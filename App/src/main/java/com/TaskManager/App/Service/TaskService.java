package com.TaskManager.App.Service;

import com.TaskManager.App.Model.Task;
import com.TaskManager.App.Model.User;
import com.TaskManager.App.Repository.TaskRepository;
import com.TaskManager.App.Repository.UserRepository;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    UserService userService;

    public List<Task> getAllTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername(authentication.getName());
        Boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_admin"));
        if(isAdmin){return taskRepo.findAll();}
        System.out.println(authentication.getAuthorities());
        return user.getTasksAssigned();
    }

    public Task getTaskById(String id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername(authentication.getName());
        Boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_admin"));
        if(isAdmin){ return taskRepo.findById(id).orElse(null);}
        return user.getTasksAssigned()
                .stream()
                .filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    public Task createTask(Task task){
        taskRepo.save(task);
        return task;
    }

    public void assignTask(String taskId, String username) {
        Task task = taskRepo.findById(taskId).orElse(null);
        User user = userRepo.findByUsername(username);
        List<Task> userTask = user.getTasksAssigned();
        if(userTask == null){userTask = new ArrayList<>();}
        userTask.add(task);
        user.setTasksAssigned(userTask);

        List<User> taskUsers =  task.getUserAssigned();
        if(taskUsers==null){ taskUsers = new ArrayList<>();}
        taskUsers.add(user);
        task.setUserAssigned(taskUsers);
        taskRepo.save(task);
        userRepo.save(user);
    }

    public void finishTask(String taskId, String username){
        Task task = taskRepo.findById(taskId).get();
        userService.deleteTaskFromUser(taskId, username);

        List<User> UserFromTask = task.getUserAssigned();
        UserFromTask.removeIf(ser -> ser.getUsername().equals(username));
        task.setUserAssigned(UserFromTask);
        taskRepo.save(task);

        if(task.getUserAssigned().isEmpty()){taskRepo.deleteById(taskId);}

    }
}

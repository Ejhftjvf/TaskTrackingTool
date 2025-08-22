package com.TaskManager.App.Service;

import com.TaskManager.App.Model.Task;
import com.TaskManager.App.Model.User;
import com.TaskManager.App.Repository.TaskRepository;
import com.TaskManager.App.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    UserRepository userRepo;
    @Autowired
    TaskRepository taskRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepo.findById(username).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    public void deleteUserByUsername(String username) {
        userRepo.deleteByUsername(username);
    }

    public void deleteTaskFromUser(String taskId, String username){
        User user = userRepo.findByUsername(username);
        List<Task> tasks = user.getTasksAssigned();
        boolean removed = tasks.removeIf(task -> task.getId().equals(taskId));
        if(removed) userRepo.save(user);
    }
}

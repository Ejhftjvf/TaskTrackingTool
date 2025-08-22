package com.TaskManager.App.Controller;

import com.TaskManager.App.Model.Task;
import com.TaskManager.App.Model.User;
import com.TaskManager.App.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping("/")
    public ResponseEntity<?> getAllTasks(){
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable String id){
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Task not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> createTask(@RequestBody Task task){
        return new ResponseEntity<>(taskService.createTask(task),HttpStatus.CREATED);
    }

    @PostMapping("/{taskId}/{username}")
    public ResponseEntity<?> assingTask(@PathVariable String taskId, @PathVariable String username){
        taskService.assignTask(taskId, username);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/{username}")
    public ResponseEntity<?> finishTask(@PathVariable String id, @PathVariable String username){
        taskService.finishTask(id,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}

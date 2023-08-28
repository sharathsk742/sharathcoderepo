package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Task;
import com.example.demo.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/tasks")
    public ResponseEntity<List<Task>> getUserAssignedTasks(@PathVariable Long userId) throws Exception {
        List<Task> assignedTasks = userService.getUserAssignedTasks(userId);
        return new ResponseEntity<>(assignedTasks, HttpStatus.OK);
    }
}

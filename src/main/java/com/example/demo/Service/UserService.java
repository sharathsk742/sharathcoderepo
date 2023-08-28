package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Task;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public List<Task> getUserAssignedTasks(Long userId) throws Exception {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            // Handle the case where user is not found, e.g., throw a custom exception
            throw new Exception("User not found with id: " + userId);
        }
        
        User user = userOptional.get();
        
        // Assuming User has a List<Task> assignedTasks field or a similar mechanism
        return user.getAssignedTasks(); // Replace with the actual method to get assigned tasks
    }
}

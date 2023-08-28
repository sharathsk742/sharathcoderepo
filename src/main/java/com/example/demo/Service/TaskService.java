package com.example.demo.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Task;
import com.example.demo.Entity.TaskStatistics;
import com.example.demo.Repository.TaskRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.model.TaskStatus;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public Task updateTask(Long taskId, Task task) throws Exception {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found with id: " + taskId));

        // Update task fields
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setDueDate(task.getDueDate());
        existingTask.setStatus(task.getStatus());
        
        if (TaskStatus.COMPLETED.equals(task.getStatus())) {
            existingTask.setCompletedDate(LocalDate.now());
        }
        
        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long taskId) throws Exception {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new Exception("Task not found with id: " + taskId));
        
        taskRepository.delete(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public void assignTask(Long taskId, Long userId) {
        Optional<Task> taskOptional = taskRepository.findById(taskId);
        if (!taskOptional.isPresent()) {
            throw new RuntimeException("Task not found with id: " + taskId);
        }
        
        Optional<com.example.demo.Entity.User> userOptional = userRepository.findById(userId); 
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + userId);
        }
        
        Task task = taskOptional.get();
        com.example.demo.Entity.User user = userOptional.get();
        
        task.setUser(user); 
        taskRepository.save(task);
    }

    public void setTaskProgress(Long taskId, int progress) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        if (progress < 0 || progress > 100) {
            throw new IllegalArgumentException("Progress value must be between 0 and 100.");
        }
        
        task.setProgress(progress);
        taskRepository.save(task);
    }

    public List<Task> getOverdueTasks() {
        LocalDate currentDate = LocalDate.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(currentDate, TaskStatus.COMPLETED);
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public List<Task> getCompletedTasksByDateRange(LocalDate startDate, LocalDate endDate) {
        return taskRepository.findByCompletedDateBetween(startDate, endDate);
    }

    public TaskStatistics getTaskStatistics() {
        long totalTasks = taskRepository.count();
        long completedTasks = taskRepository.countByStatus(TaskStatus.COMPLETED);
        double completionPercentage = (completedTasks / (double) totalTasks) * 100;
        
        return new TaskStatistics(totalTasks, completedTasks, completionPercentage);
    }
}

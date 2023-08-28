package com.example.demo.Repository;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Task;
import com.example.demo.Entity.TaskStatistics;
import com.example.demo.model.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByDueDateBeforeAndStatusNot(LocalDate currentDate, TaskStatus status);

    List<Task> findByStatus(TaskStatus status);

    List<Task> findByCompletedDateBetween(LocalDate startDate, LocalDate endDate);

    long countByStatus(TaskStatus status);
    
    Optional<Task> findById(Long taskId);
    

    
    Task save(Optional<Task> task);
    
}


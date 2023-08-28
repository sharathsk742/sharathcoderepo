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
public interface TaskStatisticsRepository extends JpaRepository<TaskStatistics, Long> {


   
    
   
}


package com.example.demo.model;

import java.util.PriorityQueue;
import java.util.Queue;

import com.example.demo.Entity.Task;

import java.time.chrono.ChronoLocalDate;
import java.util.Comparator;

public class TaskQueue {
    private Queue<Task> priorityQueue;

    public TaskQueue() {
        // Create a priority queue with a custom comparator
        priorityQueue = new PriorityQueue<>(new TaskComparator());
    }

    public void addTask(Task task) {
        priorityQueue.offer(task);
    }

    public Task getNextTask() {
        return priorityQueue.poll();
    }

    // Other methods as needed

    // Custom Comparator for Tasks
    private class TaskComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            int dateComparison = task1.getDueDate().compareTo(task2.getDueDate());
            
            if (dateComparison == 0) {
                // If due dates are the same, compare by priority (higher priority first)
                return ((ChronoLocalDate) task2.getPriority()).compareTo(task1.getPriority());
            }
            
            return dateComparison;
        }
    }
}

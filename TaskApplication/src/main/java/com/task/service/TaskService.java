package com.task.service;

import com.task.entity.Task;
import com.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repo;

    public TaskService(TaskRepository repo) 
    {
        this.repo = repo;
    }

    public boolean exists(Integer id) {
        return repo.existsById(id);
    }

    public Task save(Task t) {
        return repo.save(t);
    }

    public Task findById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }

    public List<Task> all() {
        return repo.findAll();
    }

    public List<Task> byStatus(String s) {
        return repo.findByStatusIgnoreCase(s);
    }

    public List<Task> byPriority(String p) {
        return repo.findByPriorityIgnoreCase(p);
    }

    public List<Task> overdue() {
        List<Task> list = repo.findAll();
        List<Task> result = new ArrayList<>();

        LocalDate today = LocalDate.now();

        for (Task t : list) {
            if (t.getDueDate() != null 
                && t.getDueDate().isBefore(today)
                && !t.getStatus().equalsIgnoreCase("completed")) {

                result.add(t);
            }
        }
        return result;
    }
}

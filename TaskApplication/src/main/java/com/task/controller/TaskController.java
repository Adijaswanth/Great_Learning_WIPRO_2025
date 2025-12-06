package com.task.controller;

import com.task.entity.Task;
import com.task.service.TaskService;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    private boolean isValidPriority(String p) {
        if (p == null) return true;
        return p.equalsIgnoreCase("low")
            || p.equalsIgnoreCase("medium")
            || p.equalsIgnoreCase("high");
    }

    private boolean isValidStatus(String s) {
        if (s == null) return true;
        return s.equalsIgnoreCase("pending")
            || s.equalsIgnoreCase("in-progress")
            || s.equalsIgnoreCase("completed");
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Task t) {

        if (t.getId() == null)
            return ResponseEntity.badRequest().body("id required");

        if (service.exists(t.getId()))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Task already exists");

        if (!isValidPriority(t.getPriority()))
            return ResponseEntity.badRequest().body("Invalid priority");

        if (!isValidStatus(t.getStatus()))
            return ResponseEntity.badRequest().body("Invalid status");

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(t));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Task t) {

        if (!service.exists(t.getId()))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");

        if (!isValidPriority(t.getPriority()))
            return ResponseEntity.badRequest().body("Invalid priority");

        if (!isValidStatus(t.getStatus()))
            return ResponseEntity.badRequest().body("Invalid status");

        return ResponseEntity.ok(service.save(t));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        if (!service.exists(id))
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task does not exist");

        service.delete(id);
        return ResponseEntity.ok("Task deleted");
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll() {
        return ResponseEntity.ok(service.all());
    }

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(required = false) String status) {

        if (status == null)
            return ResponseEntity.badRequest().body("status required");

        return ResponseEntity.ok(service.byStatus(status));
    }

    @GetMapping("/priority/{p}")
    public ResponseEntity<?> byPriority(@PathVariable String p) {
        return ResponseEntity.ok(service.byPriority(p));
    }

    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> overdue() {
        return ResponseEntity.ok(service.overdue());
    }
}

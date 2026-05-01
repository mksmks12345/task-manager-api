package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponse> create(@RequestBody TaskRequest request,
                                               Principal principal) {
        return ResponseEntity.ok(taskService.create(request, principal.getName()));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll(
            @RequestParam(required = false) TaskStatus status,
            Principal principal) {
        return ResponseEntity.ok(taskService.getAll(principal.getName(), status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@PathVariable Long id,
                                               @RequestBody TaskRequest request,
                                               Principal principal) {
        return ResponseEntity.ok(taskService.update(id, request, principal.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal principal) {
        taskService.delete(id, principal.getName());
        return ResponseEntity.ok(Map.of("message", "Задача удалена"));
    }
}
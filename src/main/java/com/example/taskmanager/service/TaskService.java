package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskResponse;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.TaskStatus;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    public TaskResponse create(TaskRequest request, String username) {
        User user = userService.findByUsername(username);
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus() != null ? request.getStatus() : TaskStatus.TODO);
        task.setUser(user);
        return toResponse(taskRepository.save(task));
    }

    public List<TaskResponse> getAll(String username, TaskStatus status) {
        User user = userService.findByUsername(username);
        List<Task> tasks = (status != null)
                ? taskRepository.findByUserAndStatus(user, status)
                : taskRepository.findByUser(user);
        return tasks.stream().map(this::toResponse).toList();
    }

    public TaskResponse update(Long id, TaskRequest request, String username) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Нет доступа");
        }
        if (request.getTitle() != null) task.setTitle(request.getTitle());
        if (request.getDescription() != null) task.setDescription(request.getDescription());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        return toResponse(taskRepository.save(task));
    }

    public void delete(Long id, String username) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Задача не найдена"));
        if (!task.getUser().getUsername().equals(username)) {
            throw new RuntimeException("Нет доступа");
        }
        taskRepository.delete(task);
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        return response;
    }
}
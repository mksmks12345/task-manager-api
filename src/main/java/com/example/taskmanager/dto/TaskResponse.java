package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
}
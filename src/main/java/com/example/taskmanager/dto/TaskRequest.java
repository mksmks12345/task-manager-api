package com.example.taskmanager.dto;

import com.example.taskmanager.entity.TaskStatus;
import lombok.Data;

@Data
public class TaskRequest {
    private String title;
    private String description;
    private TaskStatus status;
}
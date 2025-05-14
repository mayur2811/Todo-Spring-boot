package com.first.todo.dto;

import java.time.LocalDateTime;

import com.first.todo.model.TodoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponseDto {
     
    private Long id;
    private String title;
    private String description;
    private TodoStatus status; // Assuming status is a string representation of the enum
    private Long userId; // Assuming userId is a Long type
    private LocalDateTime createdAt; // Assuming createdAt is a string representation of the date
    private LocalDateTime UpdatedAt; // Assuming updatedAt is a string representation of the date
}

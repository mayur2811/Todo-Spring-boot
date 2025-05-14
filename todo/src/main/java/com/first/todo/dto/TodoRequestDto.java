package com.first.todo.dto;

import com.first.todo.model.TodoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto {
    
    private String title;
    private String description;
    private TodoStatus status; // Assuming status is a string representation of the enum
    private Long userId; // Assuming userId is a Long type
}

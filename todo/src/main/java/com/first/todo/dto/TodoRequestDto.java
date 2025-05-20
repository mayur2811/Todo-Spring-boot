package com.first.todo.dto;

import com.first.todo.model.TodoStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.first.todo.model.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDto extends User {
    
    private String title;
    private String description;
    private TodoStatus status; // Assuming status is a string representation of the enum
}

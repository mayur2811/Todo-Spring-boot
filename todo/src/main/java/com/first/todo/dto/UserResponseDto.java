package com.first.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.first.todo.model.Role;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    
    private Long id; // Assuming id is a Long type
    private String UserName;
    private String email;
    private Role role =Role.USER;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
     // Assuming updatedAt is a string representation of the date

}

package com.first.todo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.first.todo.model.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {
     
    private String UserName;
    private String email;
    private String password;
    private Role role= Role.USER;

}

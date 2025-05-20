package com.first.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.first.todo.service.UserService;
import com.first.todo.service.TodoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import com.first.todo.dto.UserResponseDto;
import com.first.todo.dto.UserRequestDto;
import com.first.todo.model.Role;
import com.first.todo.dto.TodoResponseDto;
import java.util.List;
import com.first.todo.dto.TodoRequestDto;
import com.first.todo.model.User;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    
    @Autowired
    private  UserService userService;
    @Autowired
    private  TodoService todoService;

    public UserController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }


    //create user
    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.registerUser(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
    }

    //get user by id
    @PostMapping("/getUserById")
    public ResponseEntity<User> getUserById(@RequestBody Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    //get all todos by user id
    @PostMapping("/getTodosByUserId")
    public ResponseEntity<List<TodoResponseDto>> getTodosByUserId(@RequestBody Long userId) {
        List<TodoResponseDto> todos = todoService.getTodosByUserId(userId);
        return ResponseEntity.ok(todos);
    }

    //update user
    @PostMapping("/updateUser")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserRequestDto userRequestDto, @RequestBody Long id) {
        // Assuming the UserRequestDto contains an id field
    
        UserResponseDto userResponseDto = userService.updateUser(id, userRequestDto);
        return ResponseEntity.ok(userResponseDto);
    }
    
    //delete user
    @PostMapping("/deleteUser")
    public ResponseEntity<Void> deleteUser(@RequestBody Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}

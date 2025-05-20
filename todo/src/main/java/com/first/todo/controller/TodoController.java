package com.first.todo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import com.first.todo.service.TodoService;  
import com.first.todo.dto.TodoResponseDto;
import com.first.todo.dto.TodoRequestDto;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.http.HttpStatus;




@RestController
@RequestMapping("/api/v1/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    
    //get all todos
    @GetMapping("/getAllTodos")
    public ResponseEntity<List<TodoResponseDto>> getAllTodos() {
        List<TodoResponseDto> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    //get todo by id
    @GetMapping("/getTodoById") 
    public ResponseEntity<TodoResponseDto> getTodoById(@RequestParam Long id) {
        TodoResponseDto todo = todoService.getTodoById(id);
        return ResponseEntity.ok(todo);
    }

    //create todo
    @GetMapping("/createTodo")
    public ResponseEntity<TodoResponseDto> createTodo(@RequestParam Long userId,@RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto todo = todoService.createTodo(todoRequestDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(todo);
    }

    //update todo
    @GetMapping("/updateTodo")
    public ResponseEntity<TodoResponseDto> updateTodo(@RequestParam Long id, @RequestBody TodoRequestDto todoRequestDto) {
        TodoResponseDto todo = todoService.updateTodo(id, todoRequestDto);
        return ResponseEntity.ok(todo);
    }

    //delete todo
    @GetMapping("/deleteTodo")
    public ResponseEntity<Void> deleteTodo(@RequestParam Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }

    
    
   
}

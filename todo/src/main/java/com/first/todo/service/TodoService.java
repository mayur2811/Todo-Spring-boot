package com.first.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.first.todo.repository.TodoRepo;
import com.first.todo.model.Todo;
import com.first.todo.dto.TodoResponseDto;
import com.first.todo.dto.TodoRequestDto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import com.first.todo.repository.UserRepo;


@Service
public class TodoService {
    
    @Autowired
    private TodoRepo todoRepo;
    @Autowired
    private UserRepo userRepo;

    public TodoService(TodoRepo todoRepo, UserRepo userRepo) {
        this.todoRepo = todoRepo;
        this.userRepo = userRepo;
    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto, Long userId) {
        // Check if the user exists
        Todo todo = new Todo();
        todo.setTitle(todoRequestDto.getTitle());
        todo.setDescription(todoRequestDto.getDescription());
        todo.setStatus(todoRequestDto.getStatus());
        todo.setUserId(userId);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());

        Todo savedTodo = todoRepo.save(todo);
        //get user 


        TodoResponseDto response = new TodoResponseDto(savedTodo.getId(), savedTodo.getTitle(), savedTodo.getDescription(), savedTodo.getStatus(), savedTodo.getUserId(), savedTodo.getCreatedAt(), savedTodo.getUpdatedAt());

        return response;
 }
    public TodoResponseDto getTodoById(Long id) {
        Todo todo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        return new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getUserId(), todo.getCreatedAt(), todo.getUpdatedAt());
    }

    public void deleteTodo(Long id) {
        Todo todo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todoRepo.delete(todo);
    }

    public TodoResponseDto updateTodo(Long id, TodoRequestDto todoRequestDto) {
        Todo todo = todoRepo.findById(id).orElseThrow(() -> new RuntimeException("Todo not found"));
        todo.setTitle(todoRequestDto.getTitle());
        todo.setDescription(todoRequestDto.getDescription());
        todo.setStatus(todoRequestDto.getStatus());
        todo.setUpdatedAt(LocalDateTime.now());

        Todo updatedTodo = todoRepo.save(todo);

        return new TodoResponseDto(updatedTodo.getId(), updatedTodo.getTitle(), updatedTodo.getDescription(), updatedTodo.getStatus(), updatedTodo.getUserId(), updatedTodo.getCreatedAt(), updatedTodo.getUpdatedAt());
    }

    public List<TodoResponseDto> getAllTodos() {
        List<Todo> todos = todoRepo.findAll();
        List<TodoResponseDto> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getUserId(), todo.getCreatedAt(), todo.getUpdatedAt()));
        }
        return response;
    }

    public List<TodoResponseDto> getTodosByUserId(Long userId) {
        List<Todo> todos = todoRepo.findByUserId(userId);
        List<TodoResponseDto> response = new ArrayList<>();
        for (Todo todo : todos) {
            response.add(new TodoResponseDto(todo.getId(), todo.getTitle(), todo.getDescription(), todo.getStatus(), todo.getUserId(), todo.getCreatedAt(), todo.getUpdatedAt()));
        }
        return response;
    }
}
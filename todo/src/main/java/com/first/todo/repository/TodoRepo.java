package com.first.todo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;   
import com.first.todo.model.Todo;
import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<Todo, Long> {
    
    List<Todo> findByUserId(Long userId);

} // Ensure this closing brace is present
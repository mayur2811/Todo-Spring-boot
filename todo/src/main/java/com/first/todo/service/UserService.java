package com.first.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import com.first.todo.dto.UserResponseDto;
import com.first.todo.dto.UserRequestDto;
import com.first.todo.repository.UserRepo;


import com.first.todo.model.User;

@Service
public class UserService {

    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto registerUser(UserRequestDto user){
        if(userRepo.existsByUsername(user.getUserName())){
            throw new RuntimeException("Username already exists");
        }
        if(userRepo.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepo.save(newUser);
         
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());   
        userResponseDto.setUserName(savedUser.getUserName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setRole(savedUser.getRole());
        userResponseDto.setCreatedAt(savedUser.getCreatedAt());
        userResponseDto.setUpdatedAt(savedUser.getUpdatedAt());
        return userResponseDto; 

    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserResponseDto updateUser(Long id, UserRequestDto user) {

        User existingUser = userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        if(user.getUserName() != null){
            existingUser.setUserName(user.getUserName());
        }
        if(user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }
        if(user.getPassword() != null){
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        existingUser.setUpdatedAt(LocalDateTime.now());
        User updatedUser = userRepo.save(existingUser);
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId(updatedUser.getId());
        userResponseDto.setUserName(updatedUser.getUserName());
        userResponseDto.setEmail(updatedUser.getEmail());
        userResponseDto.setRole(updatedUser.getRole());
        userResponseDto.setCreatedAt(updatedUser.getCreatedAt());
        userResponseDto.setUpdatedAt(updatedUser.getUpdatedAt());

        return userResponseDto;

    }

    public void deleteUser(Long id){
        User existingUser = userRepo.findById(id).orElseThrow(()-> new RuntimeException("User not found"));

        userRepo.delete(existingUser);
        if(userRepo.existsById(id)){
            throw new RuntimeException("User deletion failed");
        }
    }

    public UserResponseDto getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                        .map(user ->new UserResponseDto(user.getId(),user.getEmail(),user.getUserName(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt()))
                        .orElseThrow(() -> new RuntimeException("User not found"));     
    }

    public UserResponseDto getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .map(user -> new UserResponseDto(user.getId(), user.getUserName(), user.getEmail(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt()))
                .orElseThrow(() -> new RuntimeException("User not found"));

    }

    public boolean userExistsByUsername(String username) {
        return userRepo.existsByUsername(username);
    }
    public boolean userExistsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
    public boolean userExistsById(Long id) {
        return userRepo.existsById(id);
    }

    
}

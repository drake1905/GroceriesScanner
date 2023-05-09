package com.example.LearningProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LearningProject.models.User;
import com.example.LearningProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController{

    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{username}")
    public User getUser(@PathVariable String username){
        return userService.validateAndGetUserByUsername(username);
    }   

    @PostMapping
    public ResponseEntity createUser(@RequestBody User user) throws URISyntaxException{
        User savedUser = userService.saveUser(user);
        return ResponseEntity.created(new URI("/user/" + savedUser.getId())).body(savedUser);
    }

    @PutMapping("/update/{username}")
    public ResponseEntity updateUser(@PathVariable String username, @RequestBody User user) {
        User currentUser = userService.validateAndGetUserByUsername(username);   
        currentUser.setName(user.getName());
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
        currentUser = userService.saveUser(user);

        return ResponseEntity.ok(currentUser);
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity deleteUser(@PathVariable String username) {
        Optional<User> user = userService.getUserByUsername(username);
        userService.deleteUser(user.get());
        return ResponseEntity.ok().build();
    }
    
}
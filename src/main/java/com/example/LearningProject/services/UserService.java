package com.example.LearningProject.services;

import java.util.List;
import java.util.Optional;

import com.example.LearningProject.models.User;

public interface UserService {
    
    List<User> getUsers();
    public Optional<User> getUserByUsername(String username);
    public boolean existUserByUsername(String username);
    public User validateAndGetUserByUsername(String username);
    public Optional<User> validUsernameAndPassword(String username, String password);
    public User saveUser(User user);
    public void deleteUser(User user);
}

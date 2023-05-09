package com.example.LearningProject.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LearningProject.models.User;
import com.example.LearningProject.repository.UserRepository;
import com.example.LearningProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existUserByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public User validateAndGetUserByUsername(String username) {
        return getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username is not found"));
    }

    @Override
    public Optional<User> validUsernameAndPassword(String username, String password){
        return getUserByUsername(username).filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
    
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}

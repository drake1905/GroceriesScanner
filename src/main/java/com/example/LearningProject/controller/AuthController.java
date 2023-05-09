package com.example.LearningProject.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearningProject.controller.dto.LoginRequest;
import com.example.LearningProject.controller.dto.SignUpRequest;
import com.example.LearningProject.models.Role;
import com.example.LearningProject.models.User;
import com.example.LearningProject.services.JwtService;
import com.example.LearningProject.services.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    
    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Validated @RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        Optional<User> userOptional = userService.validUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(userOptional.isPresent()){
            var jwtToken = jwtService.generateToken(userOptional.get());
            return ResponseEntity.ok(AuthResponse.builder().token(jwtToken).build());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody SignUpRequest signUpRequest) throws Exception{
        if(userService.existUserByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User user = userService.saveUser(createUser(signUpRequest));
        var jwtToken = jwtService.generateToken(user);

        return ResponseEntity.ok(AuthResponse.builder().token(jwtToken).build());
    }

    private User createUser(SignUpRequest signUpRequest){
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setRole(Role.ROLE_USER);

        return user;
    }
}

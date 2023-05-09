package com.example.LearningProject.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "users")
public class User {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private @Id Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public User(String name,String username,String email,String password){
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}

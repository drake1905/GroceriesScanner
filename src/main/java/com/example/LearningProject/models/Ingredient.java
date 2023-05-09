package com.example.LearningProject.models;

import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "ingredients")
public class Ingredient {
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ingredient_id")
    private @Id Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "ingredientsList")
    Set<Scanner> lists;
}

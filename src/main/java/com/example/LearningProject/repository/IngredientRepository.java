package com.example.LearningProject.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearningProject.models.Ingredient;
import com.example.LearningProject.models.Scanner;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{
    
    Optional<Ingredient> findByName(String name);
    boolean existsByName(String name);
    List<Ingredient> findByScanner(Scanner scanner);
}

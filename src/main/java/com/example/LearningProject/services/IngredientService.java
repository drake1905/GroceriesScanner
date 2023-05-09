package com.example.LearningProject.services;

import java.util.List;
import java.util.Optional;

import com.example.LearningProject.models.Ingredient;
import com.example.LearningProject.models.Scanner;

public interface IngredientService {
    List<Ingredient> getIngredients();
    List<Ingredient> getIngredientsByScanner(Scanner scanner);
    public Optional<Ingredient> getIngredientByName(String name);
    public boolean existIngredientByName(String name);
    public Ingredient saveIngredient(Ingredient ingredient);
    public void deleteIngredient(Ingredient ingredient);
}

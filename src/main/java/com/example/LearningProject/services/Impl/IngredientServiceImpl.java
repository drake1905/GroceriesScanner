package com.example.LearningProject.services.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.LearningProject.repository.IngredientRepository;
import com.example.LearningProject.services.IngredientService;
import com.example.LearningProject.models.Ingredient;
import com.example.LearningProject.models.Scanner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getIngredients(){
        return ingredientRepository.findAll();
    }

    @Override
    public List<Ingredient> getIngredientsByScanner(Scanner scanner){
        return ingredientRepository.findByScanner(scanner);
    }

    @Override
    public Optional<Ingredient> getIngredientByName(String name){
        return ingredientRepository.findByName(name);
    }

    @Override
    public boolean existIngredientByName(String name){
        return ingredientRepository.existsByName(name);
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient){
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(Ingredient ingredient){
        ingredientRepository.delete(ingredient);
    }
}

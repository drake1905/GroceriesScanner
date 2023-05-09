package com.example.LearningProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LearningProject.models.Ingredient;
import com.example.LearningProject.services.IngredientService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ingredient")
public class IngredientController {
    
    private final IngredientService ingredientService;

    @GetMapping
    public List<Ingredient> getIngredients(){
        return ingredientService.getIngredients();
    }

    @GetMapping("/{name}")
    public Ingredient getIngredient(@PathVariable String name){
        return ingredientService.getIngredientByName(name).get();
    }

    @PostMapping
    public ResponseEntity createIngredient(@RequestBody Ingredient ingredient) throws URISyntaxException{
        Ingredient savedIngredient = ingredientService.saveIngredient(ingredient);
        return ResponseEntity.created(new URI("/ingredient/" + savedIngredient.getId())).body(savedIngredient);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteIngredient(@PathVariable String name) {
        Optional<Ingredient> ingredient = ingredientService.getIngredientByName(name);
        ingredientService.deleteIngredient(ingredient.get());
        return ResponseEntity.ok().build();
    }
}

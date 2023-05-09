package com.example.LearningProject.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LearningProject.models.Ingredient;
import com.example.LearningProject.models.Scanner;
import com.example.LearningProject.services.ScannerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/scanner")
public class ScannerController {
    
    private final ScannerService scannerService;

    @GetMapping
    public List<Scanner> getScanners(){
        return scannerService.getScanners();
    }

    @GetMapping("/{name}")
    public Scanner getScanner(@PathVariable String name){
        return scannerService.getScannerByName(name).get();
    }

    @GetMapping("/validateProduct")
    public boolean validateProduct(@RequestBody Set<String> productIngredients, @RequestBody Long scannerId){
        Set<Ingredient> scannerIngredients = scannerService.getScannerById(scannerId).get().getIngredientsList();
        Set<String> scannerNameList = scannerIngredients.stream().map(Ingredient::getName).collect(Collectors.toSet()); 
        return scannerService.validateScannerList(productIngredients, scannerNameList);
    }

    @PostMapping
    public ResponseEntity createScanner(@RequestBody Scanner scanner) throws URISyntaxException{
        Scanner savedScanner = scannerService.saveScanner(scanner);
        return ResponseEntity.created(new URI("/scanner/" + savedScanner.getId())).body(savedScanner);
    }

    @DeleteMapping("/delete/{name}")
    public ResponseEntity deleteScanner(@PathVariable String name) {
        Optional<Scanner> scanner = scannerService.getScannerByName(name);
        scannerService.deleteScanner(scanner.get());
        return ResponseEntity.ok().build();
    }
}

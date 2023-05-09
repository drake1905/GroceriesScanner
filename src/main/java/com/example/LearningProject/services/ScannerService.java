package com.example.LearningProject.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.LearningProject.models.Scanner;

public interface ScannerService {
    
    List<Scanner> getScanners();
    public Optional<Scanner> getScannerById(Long id);
    public Optional<Scanner> getScannerByName(String name);
    public boolean existScannerByName(String name);
    public Scanner saveScanner(Scanner scanner);
    public void deleteScanner(Scanner scanner);
    public boolean validateScannerList(Set<String> productIngredients, Set<String> scannerIngredients);
    public List<String> listOfIngredientNames(Scanner scanner);
}

package com.example.LearningProject.services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.LearningProject.repository.ScannerRepository;
import com.example.LearningProject.services.ScannerService;
import com.example.LearningProject.models.Scanner;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ScannerServiceImpl implements ScannerService {

    private final ScannerRepository scannerRepository;

    @Override
    public List<Scanner> getScanners(){
        return scannerRepository.findAll();
    }

    @Override
    public Optional<Scanner> getScannerById(Long id){
        return scannerRepository.findById(id);
    }

    @Override
    public Optional<Scanner> getScannerByName(String name){
        return scannerRepository.findByName(name);
    }

    @Override
    public boolean existScannerByName(String name){
        return scannerRepository.existsByName(name);
    }

    @Override
    public Scanner saveScanner(Scanner scanner){
        return scannerRepository.save(scanner);
    }

    @Override
    public void deleteScanner(Scanner scanner){
        scannerRepository.delete(scanner);
    }

    @Override
    public boolean validateScannerList(Set<String> productIngredients, Set<String> scannerIngredients){
        productIngredients.retainAll(scannerIngredients);
        if(productIngredients.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public List<String> listOfIngredientNames(Scanner scanner){

        return null;
    }

}

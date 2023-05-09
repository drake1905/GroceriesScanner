package com.example.LearningProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearningProject.models.Scanner;

public interface ScannerRepository extends JpaRepository<Scanner, Long> {

    Optional<Scanner> findByName(String name);
    boolean existsByName(String name);
}

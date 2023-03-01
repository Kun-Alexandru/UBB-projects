package com.example.lab1spring.model;

import java.time.LocalDateTime;

public record Animal (
    String id,
    String name,
    String breed,
    int number_of_legs,
    String description,
    LocalDateTime dateOfBirth
){}

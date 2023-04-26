package com.example.lab2.model;

import java.time.LocalDateTime;

public class AnimalDTO {
    private long id;
    private String name;
    private String breed;
    private int number_of_legs;
    private String description;
    private LocalDateTime dateOfBirth;
    private Boolean isNeutered;
    private ShelterDTO shelter;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getNumber_of_legs() {
        return number_of_legs;
    }

    public void setNumber_of_legs(int number_of_legs) {
        this.number_of_legs = number_of_legs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Boolean getNeutered() {
        return isNeutered;
    }

    public void setNeutered(Boolean neutered) {
        isNeutered = neutered;
    }

    public ShelterDTO getShelter() {
        return shelter;
    }

    public void setShelter(ShelterDTO shelter) {
        this.shelter = shelter;
    }

    public AnimalDTO(long id, String name, String breed, int number_of_legs, String description, LocalDateTime dateOfBirth, Boolean isNeutered, ShelterDTO shelter) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.number_of_legs = number_of_legs;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.isNeutered = isNeutered;
        this.shelter = shelter;
    }
}

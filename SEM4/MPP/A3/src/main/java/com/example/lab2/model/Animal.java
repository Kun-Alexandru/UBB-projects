package com.example.lab2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animal_generator")
    private long id;

    private String name;
    private String breed;

    @Override
    public String toString() {
        return "Animal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", number_of_legs=" + number_of_legs +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", isNeutered=" + isNeutered +
                ", shelter=" + shelter +
                '}';
    }

    private int number_of_legs;
    private String description;
    private LocalDateTime dateOfBirth;
    private Boolean isNeutered;

    private long shId;

    public long getShId() {
        return shId;
    }

    public void setShId(long shId) {
        this.shId = shId;
    }

    public Animal(String name, String breed, int number_of_legs, String description, LocalDateTime dateOfBirth, Boolean isNeutered) {
        this.name = name;
        this.breed = breed;
        this.number_of_legs = number_of_legs;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.isNeutered = isNeutered;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shelter_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shelter shelter;

    public Animal(){}

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

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }
}

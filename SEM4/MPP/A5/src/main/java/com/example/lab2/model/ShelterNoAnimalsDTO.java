package com.example.lab2.model;

public class ShelterNoAnimalsDTO {

    private long id;
    private String name;
    private String location;
    private long numberOfAnimals;

    public ShelterNoAnimalsDTO(long id, String name, String location, long numberOfAnimals) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.numberOfAnimals = numberOfAnimals;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void setNumberOfAnimals(long numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }
}

package com.example.lab2.model;

import java.time.LocalDateTime;

public class ShelterDTO {
    private Long id;
    private String location;
    private String name;
    private float usable_area_in_sq;
    private String description;
    private LocalDateTime dateOfConstruction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getUsable_area_in_sq() {
        return usable_area_in_sq;
    }

    public void setUsable_area_in_sq(float usable_area_in_sq) {
        this.usable_area_in_sq = usable_area_in_sq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateOfConstruction() {
        return dateOfConstruction;
    }

    public void setDateOfConstruction(LocalDateTime dateOfConstruction) {
        this.dateOfConstruction = dateOfConstruction;
    }

    public ShelterDTO(Long id, String location, String name, float usable_area_in_sq, String description, LocalDateTime dateOfConstruction) {
        this.id = id;
        this.location = location;
        this.name = name;
        this.usable_area_in_sq = usable_area_in_sq;
        this.description = description;
        this.dateOfConstruction = dateOfConstruction;
    }
}

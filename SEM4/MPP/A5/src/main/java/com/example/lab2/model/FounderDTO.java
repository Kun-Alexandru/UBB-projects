package com.example.lab2.model;

import java.time.LocalDateTime;

public class FounderDTO {
    private long id;
    private String name;
    private String occupation;
    private Boolean isMarried;
    private String description;
    private LocalDateTime dateOfBirth;

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

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Boolean getMarried() {
        return isMarried;
    }

    public void setMarried(Boolean married) {
        isMarried = married;
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

    public FounderDTO(long id, String name, String occupation, Boolean isMarried, String description, LocalDateTime dateOfBirth) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.isMarried = isMarried;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
    }
}

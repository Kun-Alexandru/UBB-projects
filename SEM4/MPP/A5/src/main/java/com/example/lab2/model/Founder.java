package com.example.lab2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "founders")
public class Founder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "founder_generator")
    private long id;

    @NotBlank(message = "Founder name is mandatory")
    private String name;
    private String occupation;
    private Boolean isMarried;
    private String description;
    private LocalDateTime dateOfBirth;

    @OneToMany(mappedBy="founder")
    private Set<Investment> investments;

    public Founder(){};

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
        this.investments = investments;
    }

    public Founder(String name, String occupation, Boolean isMarried, String description, LocalDateTime dateOfBirth) {
        this.name = name;
        this.occupation = occupation;
        this.isMarried = isMarried;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", occupation='" + occupation + '\'' +
                ", isMarried=" + isMarried +
                ", description='" + description + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}

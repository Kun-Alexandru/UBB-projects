package com.example.lab2.model;

import java.time.LocalDateTime;

public class FounderAvgDTO {

    private long id;
    private String name;
    private Double avgInvestment;

    public FounderAvgDTO(long id, String name, Double avgInvestment) {
        this.id = id;
        this.name = name;
        this.avgInvestment = avgInvestment;
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


    public Double getAvgInvestment() {
        return avgInvestment;
    }

    public void setAvgInvestment(Double avgInvestment) {
        this.avgInvestment = avgInvestment;
    }

    // getters and setters omitted for brevity

}

package com.example.lab2.model;

public class FounderIntDTO {
    private long id;
    private String name;
    private int maxInvestment;

    public FounderIntDTO(long id, String name, int maxInvestment) {
        this.id = id;
        this.name = name;
        this.maxInvestment = maxInvestment;
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

    public int getMaxInvestment() {
        return maxInvestment;
    }

    public void setMaxInvestment(int maxInvestment) {
        this.maxInvestment = maxInvestment;
    }
}

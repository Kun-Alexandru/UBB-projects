package com.example.lab2.model;

public class InvestmentDTO {
    private long id;
    private int investedMoney;
    private int ownedPercentage;
    private ShelterDTO shelter;

    private FounderDTO founder;

    public InvestmentDTO(long id, int investedMoney, int ownedPercentage, ShelterDTO shelter, FounderDTO founder) {
        this.id = id;
        this.investedMoney = investedMoney;
        this.ownedPercentage = ownedPercentage;
        this.shelter = shelter;
        this.founder = founder;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getInvestedMoney() {
        return investedMoney;
    }

    public void setInvestedMoney(int investedMoney) {
        this.investedMoney = investedMoney;
    }

    public int getOwnedPercentage() {
        return ownedPercentage;
    }

    public void setOwnedPercentage(int ownedPercentage) {
        this.ownedPercentage = ownedPercentage;
    }

    public ShelterDTO getShelter() {
        return shelter;
    }

    public void setShelter(ShelterDTO shelter) {
        this.shelter = shelter;
    }

    public FounderDTO getFounder() {
        return founder;
    }

    public void setFounder(FounderDTO founder) {
        this.founder = founder;
    }
}

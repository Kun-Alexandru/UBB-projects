package com.example.lab2.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Optional;

@Entity
@Table(name = "investments")
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "investment_generator")
    private long id;
    private int investedMoney;
    private int ownedPercentage;
    private long fId;
    private long sId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "shelter_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Shelter shelter;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "founder_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Founder founder;

    public Investment() {
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

    public long getfId() {
        return fId;
    }

    public void setfId(long fId) {
        this.fId = fId;
    }

    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }

    public Shelter getShelter() {
        return shelter;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Founder getFounder() {
        return founder;
    }

    public void setFounder(Founder founder) {
        this.founder = founder;
    }

    public Investment(int investedMoney, int ownedPercentage) {
        this.investedMoney = investedMoney;
        this.ownedPercentage = ownedPercentage;
    }
}

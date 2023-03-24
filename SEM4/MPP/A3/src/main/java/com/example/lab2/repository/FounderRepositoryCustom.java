package com.example.lab2.repository;

import com.example.lab2.model.Founder;
import com.example.lab2.model.FounderAvgDTO;
import com.example.lab2.model.FounderIntDTO;

import java.util.List;

public interface FounderRepositoryCustom {
    List<FounderAvgDTO> find2();

    List<FounderIntDTO> find3();
}

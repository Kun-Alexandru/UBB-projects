package com.example.lab2.Service;

import com.example.lab2.model.ShelterNoAnimalsDTO;
import com.example.lab2.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ShelterService {
    @Autowired
    private ShelterRepository repo;

    public List<ShelterNoAnimalsDTO> orderShelterByNumberAnimals(){
        return repo.find1();
    }
}

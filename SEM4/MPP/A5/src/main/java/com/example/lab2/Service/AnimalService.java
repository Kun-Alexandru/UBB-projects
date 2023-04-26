package com.example.lab2.Service;

import com.example.lab2.model.Animal;
import com.example.lab2.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository repo;

    public AnimalService(AnimalRepository repo) {
        this.repo = repo;
    }


    public List<Animal> getAll(){
        return repo.findAll();
    }
    public List<Animal> filterAnimal(int numberLegs){
        return repo.findAll().stream().filter(r -> r.getNumber_of_legs() > numberLegs).collect(Collectors.toList());
    }
}

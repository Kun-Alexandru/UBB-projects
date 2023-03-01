package com.example.lab1spring.controller;

import com.example.lab1spring.exception.AnimalException;
import com.example.lab1spring.model.Animal;
import com.example.lab1spring.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {
    private final AnimalRepository repository;

    @Autowired
    public AnimalController(AnimalRepository repository){this.repository=repository;}

    @GetMapping
    public List<Animal> findAll(){return repository.findAll();}

    @GetMapping("/{id}")
    public Animal findById(@PathVariable String id) throws AnimalException{
        return repository.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Animal create(@RequestBody Animal animal) {
        return repository.create(animal);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Animal animal, @PathVariable String id) {
        repository.update(animal,id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        repository.delete(id);
    }

}

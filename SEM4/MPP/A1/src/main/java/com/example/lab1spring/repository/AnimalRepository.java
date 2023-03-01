package com.example.lab1spring.repository;

import com.example.lab1spring.model.Animal;
import com.example.lab1spring.exception.AnimalException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class AnimalRepository {
    List <Animal> animals = new ArrayList<>();
    public AnimalRepository(){
        animals.add(new Animal(
                UUID.randomUUID().toString(),
                "TestName",
                "TestBreed",
                4,
                "Test_description",
                LocalDateTime.of(2023,3,1,0,0)
        ));
    }
    public List<Animal> findAll(){return animals;}
    public Animal findById(String id) throws AnimalException{
        return animals.stream().filter(animal -> animal.id().equals(id)).findFirst().orElseThrow(AnimalException::new);
    }
    public Animal create(Animal animal){
        if (animals.contains(animal))
            return animal;
        animals.add(animal);
        return animal;
    }
    public void update(Animal animal, String id){
        Animal existing = animals.stream().filter(s -> s.id().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stream not found"));
        int i = animals.indexOf(existing);
        animals.set(i,animal);
    }
    public void delete(String id){ animals.removeIf(animal -> animal.id().equals(id));}
}

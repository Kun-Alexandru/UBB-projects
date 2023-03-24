package com.example.lab2.controller;

import com.example.lab2.exception.ResourceNotFoundException;
import com.example.lab2.model.Animal;
import com.example.lab2.model.AnimalDTO;
import com.example.lab2.model.Shelter;
import com.example.lab2.model.ShelterDTO;
import com.example.lab2.repository.AnimalRepository;
import com.example.lab2.repository.ShelterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class AnimalController {
    @Autowired
    private AnimalRepository animalRepository;
    @Autowired
    private ShelterRepository shelterRepository;

    @GetMapping("shelters/{shelter_id}/animals")
    public ResponseEntity<List<Animal>> getAllAnimalsByShelterId(@PathVariable(value = "shelter_id") Long shelterId){
        if(!shelterRepository.existsById(shelterId))
            throw new ResourceNotFoundException("Not found Shelter with id = " + shelterId);
        List<Animal> animals = animalRepository.findByShelterId(shelterId);
        if(animals.isEmpty())
            throw new ResourceNotFoundException("Didn't find any animals in shelther with id " + shelterId);
        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/animals")
    public ResponseEntity<List<Animal>> getAllAnimals(@RequestParam(required = false) String name){
        List<Animal> animals = new ArrayList<Animal>();
        animalRepository.findAll().forEach(animals::add);
        if (animals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(animals, HttpStatus.OK);
    }

    @GetMapping("/animals/filter/{value}")
    public ResponseEntity<List<Animal>> filterAnimals(@PathVariable(value = "value") int numberLegs){
        List<Animal> animals = new ArrayList<Animal>();
        animalRepository.findAll().forEach(animals::add);
        List<Animal> animalsFiltered = animals.stream().filter(a -> a.getNumber_of_legs() > numberLegs).toList();
        if (animalsFiltered.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(animalsFiltered, HttpStatus.OK);
    }

    @PostMapping("/shelters/{shelter_id}/animals")
    public ResponseEntity<Animal> addAnimal(@PathVariable(value = "shelter_id") Long shelterId,@RequestBody Animal animalRequest){
        Animal animal = shelterRepository.findById(shelterId).map(sh -> {
            animalRequest.setShelter(sh);
            animalRequest.setShId(shelterId);
            return animalRepository.save(animalRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Shelter with id = " + shelterId));
        return new ResponseEntity<>(animal, HttpStatus.CREATED);
    }

    @PutMapping("/animals/{id}")
    public ResponseEntity<Animal> updateAnimal(@PathVariable("id") long id, @RequestBody Animal animalRequest){
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal " + id + "not found"));

        animal.setBreed(animalRequest.getBreed());
        animal.setName(animalRequest.getName());
        animal.setDescription(animalRequest.getDescription());
        animal.setDateOfBirth(animalRequest.getDateOfBirth());
        animal.setNumber_of_legs(animalRequest.getNumber_of_legs());

        return new ResponseEntity<>(animalRepository.save(animal), HttpStatus.OK);
    }

    @PutMapping("/animals/{idAnimal}/{idShelter}")
    public ResponseEntity<Animal> updateAnimalShelter(@PathVariable("idAnimal") long id, @PathVariable("idShelter") long idShelter, @RequestBody Animal animalRequest) throws ResourceNotFoundException{
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal " + id + "not found"));

        animal.setBreed(animalRequest.getBreed());
        animal.setName(animalRequest.getName());
        animal.setDescription(animalRequest.getDescription());
        animal.setDateOfBirth(animalRequest.getDateOfBirth());
        animal.setNumber_of_legs(animalRequest.getNumber_of_legs());
        Shelter shelter = null;
        shelter = shelterRepository.findShelterById(idShelter);
        if(shelter == null)
            throw new ResourceNotFoundException("Shelter id " + idShelter + "not found");
        animal.setShelter(shelter);

        return new ResponseEntity<>(animalRepository.save(animal), HttpStatus.OK);
    }

    @DeleteMapping("/animals/{id}")
    public ResponseEntity<HttpStatus> deleteAnimal(@PathVariable("id") long id){
        Animal animal = animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal " + id + "not found"));
        animalRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/animals/{id}")
    public ResponseEntity<AnimalDTO> getAnimalById(@PathVariable("id") long id){
        Animal animal = animalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Animal with id = " + id));
        AnimalDTO animalDTO = null;
        Shelter shelter = animal.getShelter();
        ShelterDTO shelterDTO = new ShelterDTO(shelter.getId(),shelter.getLocation(),shelter.getName(),shelter.getUsable_area_in_sq(),shelter.getDescription(),shelter.getDateOfConstruction());
        animalDTO = new AnimalDTO(animal.getId(),animal.getName(),animal.getBreed(),animal.getNumber_of_legs(),
                animal.getDescription(),animal.getDateOfBirth(),animal.getNeutered(),shelterDTO);
        return new ResponseEntity<>(animalDTO, HttpStatus.OK);
    }
}

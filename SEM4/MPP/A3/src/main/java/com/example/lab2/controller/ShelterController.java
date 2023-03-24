package com.example.lab2.controller;

import com.example.lab2.exception.ResourceNotFoundException;
import com.example.lab2.model.FounderIntDTO;
import com.example.lab2.model.Shelter;
import com.example.lab2.model.ShelterDTO;
import com.example.lab2.model.ShelterNoAnimalsDTO;
import com.example.lab2.repository.ShelterRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class ShelterController {
    @Autowired
    ShelterRepository shelterRepository;

    @GetMapping("/shelters")
    public ResponseEntity<List<ShelterDTO>> getAllShelters(@RequestParam(required = false) String name) {
        List<Shelter> shelters = new ArrayList<Shelter>();
        if (name == null)
            shelterRepository.findAll().forEach(shelters::add);
        else
            shelterRepository.findByNameContaining(name).forEach(shelters::add);

        List<ShelterDTO> shelterDTOS = new ArrayList<>();

        for(int i = 0; i < shelters.size(); i++){
            ShelterDTO shelterDTO = new ShelterDTO(shelters.get(i).getId(),shelters.get(i).getLocation(),shelters.get(i).getName(),
                    shelters.get(i).getUsable_area_in_sq(),shelters.get(i).getDescription(),shelters.get(i).getDateOfConstruction());
            shelterDTOS.add(shelterDTO);
        }

        if(shelterDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shelterDTOS, HttpStatus.OK);
    }

    @GetMapping("/shelters/{id}")
    public ResponseEntity<Shelter> getShelterById(@PathVariable("id") long id){
        Shelter shelter = shelterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Shelter with id = " + id));
        return new ResponseEntity<>(shelter, HttpStatus.OK);
    }

    @PostMapping("/shelters")
    public ResponseEntity<Shelter> createShelter(@RequestBody Shelter shelter){
        Shelter _shelter = shelterRepository.save(new Shelter(shelter.getLocation(),shelter.getName(),
                shelter.getUsable_area_in_sq(),shelter.getDescription(),shelter.getDateOfConstruction()));
        return new ResponseEntity<>(_shelter, HttpStatus.CREATED);
    }

    @PutMapping("/shelters/{id}")
    public ResponseEntity<Shelter> updateShelter(@PathVariable("id") long id,@RequestBody Shelter shelter){
        Shelter _shelter = shelterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Shelter with id = " + id));

        _shelter.setDescription(shelter.getDescription());
        _shelter.setName(shelter.getName());
        _shelter.setLocation(shelter.getLocation());
        _shelter.setDateOfConstruction(shelter.getDateOfConstruction());
        _shelter.setUsable_area_in_sq(shelter.getUsable_area_in_sq());
        return new ResponseEntity<>(shelterRepository.save(_shelter), HttpStatus.OK);
    }

    @DeleteMapping("/shelters/{id}")
    public ResponseEntity<HttpStatus> deleteShelter(@PathVariable("id") long id){
        Shelter _shelter = shelterRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Shelter with id = " + id));
        shelterRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/shelters/report1")
    public ResponseEntity<List<ShelterNoAnimalsDTO>> findStatistics1(){
        return new ResponseEntity<>(shelterRepository.OrderShelterAscNoAnimals(), HttpStatus.OK);
    }

    @GetMapping("/shelters/report2")
    public ResponseEntity<List<ShelterNoAnimalsDTO>> findStatistics2(){
        return new ResponseEntity<>(shelterRepository.find1(), HttpStatus.OK);
    }
}

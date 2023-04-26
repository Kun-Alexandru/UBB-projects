package com.example.lab2.controller;


import com.example.lab2.exception.ResourceNotFoundException;
import com.example.lab2.model.*;
import com.example.lab2.repository.FounderRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class FounderController {
    @Autowired
    FounderRepository founderRepository;

    @PostMapping("/founders")
    public ResponseEntity<Founder> createFounder(@Valid @RequestBody Founder founder){
        Founder _founder = founderRepository.save(new Founder(founder.getName(),founder.getOccupation(),
                founder.getMarried(),founder.getDescription(),founder.getDateOfBirth()));
        return new ResponseEntity<>(_founder, HttpStatus.CREATED);
    }

    @GetMapping("/founders")
    public ResponseEntity<List<FounderDTO>> getAllFounders(@RequestParam(required = false) String name) {
        List<Founder> founders = new ArrayList<Founder>();
        if (name == null)
            founderRepository.findAll().forEach(founders::add);
        else
            founderRepository.findByNameContaining(name).forEach(founders::add);

        List<FounderDTO> founderDTOS = new ArrayList<>();

        for(int i = 0; i < founders.size(); i++){
            FounderDTO founderDTO = new FounderDTO(founders.get(i).getId(),founders.get(i).getName(),founders.get(i).getOccupation(),
                    founders.get(i).getMarried(),founders.get(i).getDescription(),founders.get(i).getDateOfBirth());
            founderDTOS.add(founderDTO);
        }

        if(founderDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(founderDTOS, HttpStatus.OK);
    }

    @GetMapping("/founders/{id}")
    public ResponseEntity<Founder> getFounderById(@PathVariable("id") long id){
        Founder founder = founderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found founder with id = " + id));
        return new ResponseEntity<>(founder, HttpStatus.OK);
    }

    @PutMapping("/founders/{id}")
    public ResponseEntity<Founder> updateFounder(@PathVariable("id") long id,@Valid @RequestBody Founder founder){
        Founder _founder = founderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Founder with id = " + id));

        _founder.setDescription(founder.getDescription());
        _founder.setName(founder.getName());
        _founder.setMarried(founder.getMarried());
        _founder.setOccupation(founder.getOccupation());
        _founder.setDateOfBirth(founder.getDateOfBirth());

        return new ResponseEntity<>(founderRepository.save(_founder), HttpStatus.OK);
    }

    @DeleteMapping("/founders/{id}")
    public ResponseEntity<HttpStatus> deleteFounder(@PathVariable("id") long id){
        Founder _founder = founderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found Shelter with id = " + id));
        founderRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/founders/avgV2")
    public ResponseEntity<List<FounderAvgDTO>> findAvgStasticsV2(){
        return new ResponseEntity<>(founderRepository.find2(), HttpStatus.OK);
    }

    @GetMapping("/founders/avgV1")
    public ResponseEntity<List<FounderAvgDTO>> findAvgStasticsV1(){
        return new ResponseEntity<>(founderRepository.OrderFoundersByAvgInvestment(), HttpStatus.OK);
    }

    @GetMapping("/founders/avgV3")
    public ResponseEntity<List<FounderIntDTO>> findAvgStasticsV3(){
        return new ResponseEntity<>(founderRepository.find3(), HttpStatus.OK);
    }
}

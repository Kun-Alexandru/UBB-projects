package com.example.lab1spring.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnimalTest {

    @Test
    void create_new_animal(){
        Animal animal = new Animal("1","T","B",4,"D",LocalDateTime.of(2023,3,1,0,0));
        assertNotNull(animal);
        assertEquals("T",animal.name());
        assertTrue(animal.getClass().isRecord());
        assertEquals(6,animal.getClass().getRecordComponents().length);
    }
}

package com.example.lab2.repository;
import com.example.lab2.model.Animal;
import com.example.lab2.model.AnimalDTOSpecial;
import com.example.lab2.model.FounderAvgDTO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal,Long> {

    List<Animal> findByShelterId(Long shelterId);

    Animal findAnimalsById(Long id);

    @Transactional
    void deleteByShelterId(long shelterId);
}

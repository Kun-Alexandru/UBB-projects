package com.example.lab2.repository;

import com.example.lab2.model.Animal;
import com.example.lab2.model.Founder;
import com.example.lab2.model.Investment;
import com.example.lab2.model.Shelter;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment,Long>{
    List<Investment> findByShelterId(Long id);
    List<Investment> findByFounderId(Long id);
}
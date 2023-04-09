package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Denda;

public interface DendaRepository extends JpaRepository<Denda, Integer> {
    
}

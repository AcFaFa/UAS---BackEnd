package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Pengembalian;

public interface PengembalianRepository extends JpaRepository<Pengembalian, Integer> {
    
}

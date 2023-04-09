package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Peminjaman;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {
    
}

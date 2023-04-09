package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Petugas;

public interface PetugasRepository extends JpaRepository<Petugas, Integer> {
    @Query("SELECT an from Pengarang an WHERE an.name LIKE %:name%")
    public List<Petugas> findByName(String name);
}

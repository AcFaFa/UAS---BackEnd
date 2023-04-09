package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Anggota;

public interface AnggotaRepository extends JpaRepository<Anggota, Integer> {
    @Query("SELECT an from Anggota an WHERE an.name LIKE %:name%")
    public List<Anggota> findByName(String name);
}

package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Buku;

public interface BukuRepository extends JpaRepository<Buku, Integer> {
    @Query("SELECT t from Buku t WHERE t.title like %:titleBuku%")
    public List<Buku> findByTitle(String titleBuku);   
}

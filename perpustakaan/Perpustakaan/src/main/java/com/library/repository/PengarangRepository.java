package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Pengarang;

public interface PengarangRepository extends JpaRepository<Pengarang, Integer> {
    @Query("SELECT an from Pengarang an WHERE an.name LIKE %:name%")
    public List<Pengarang> findByName(String name);
}

package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Akun;

public interface AkunRepository extends JpaRepository<Akun, Integer> {
    @Query("SELECT acc FROM Akun acc WHERE acc.username LIKE %:username%")
    public Optional<Akun> validasiAkun(String username);
}

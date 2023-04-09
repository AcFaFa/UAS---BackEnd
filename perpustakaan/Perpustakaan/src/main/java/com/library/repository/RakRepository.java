package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.library.model.Rak;

public interface RakRepository extends JpaRepository<Rak, Integer> {
    @Query("SELECT g from Rak g WHERE g.genre LIKE %:Genre%")
    public List<Rak> findByGenre(String Genre);

    @Query("SELECT g from Rak g WHERE g.type LIKE %:Type%")
    public List<Rak> findByType(String Type);

    @Query("SELECT g from Rak g WHERE g.rackCode LIKE %:RackCode%")
    public List<Rak> findByRackCode(String RackCode);
}

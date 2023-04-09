package com.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.RakDto;
import com.library.model.Rak;
import com.library.repository.RakRepository;

@RestController
@RequestMapping("/rak")
public class RakController {
    
    RakRepository rakRepository;

    @Autowired
    RakController(RakRepository rakRepository) {
        this.rakRepository = rakRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return rakRepository.findAll();
    }

    @PostMapping("/tambah-rak")
    public Object insert(@RequestBody RakDto dto) {

        Rak rak = new Rak();
        rak.setGenre(dto.getGenre());
        rak.setType(dto.getType());
        rak.setRackCode(dto.getRackCode());

        return ResponseEntity.ok(rakRepository.save(rak));

    }

    @PutMapping("/edit-rak/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody RakDto dto) {

        Rak rak = rakRepository.findById(id).orElse(null);

        if (rak == null) {
            return ResponseEntity.badRequest().body("ID Rak tidak ditemukan!");
        }

        rak.setGenre(dto.getGenre());
        rak.setType(dto.getType());
        rak.setRackCode(dto.getRackCode());

        return ResponseEntity.ok(rakRepository.save(rak));

    }

    // @DeleteMapping("/hapus-rak/{id}")
    // public Object delete(@PathVariable("id") Integer id) {

    //     Rak rak = rakRepository.findById(id).orElse(null);

    //     if (rak == null) {
    //         return ResponseEntity.badRequest().body("ID Rak tidak ditemukan!");
    //     }

    //     rakRepository.delete(rak);

    //     return ResponseEntity.ok("Data rak berhasil dihapus");

    // }

}

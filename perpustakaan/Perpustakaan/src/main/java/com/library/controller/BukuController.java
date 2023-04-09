package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.BukuDto;
import com.library.model.Buku;
import com.library.model.Penerbit;
import com.library.model.Pengarang;
import com.library.model.Rak;
import com.library.repository.BukuRepository;
import com.library.repository.PenerbitRepository;
import com.library.repository.PengarangRepository;
import com.library.repository.RakRepository;

@RestController
@RequestMapping("/buku")
public class BukuController {
    
    BukuRepository bukuRepository;
    PengarangRepository pengarangRepository;
    PenerbitRepository penerbitRepository;
    RakRepository rakRepository;

    @Autowired
    BukuController(BukuRepository bukuRepository, PengarangRepository pengarangRepository, PenerbitRepository penerbitRepository, RakRepository rakRepository) {
        this.bukuRepository = bukuRepository;
        this.pengarangRepository = pengarangRepository;
        this.penerbitRepository = penerbitRepository;
        this.rakRepository = rakRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return bukuRepository.findAll();
    }

    @GetMapping("/find-by-title")
    public Object findBuku(@RequestParam("titleBuku") String titleBuku) {
        return bukuRepository.findByTitle(titleBuku);
    }

    @PostMapping("/tambah-buku")
    public Object insert(@RequestBody BukuDto dto) {

        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = s.format(date);

        Buku buku = new Buku();
        buku.setTitle(dto.getTitle());
        buku.setStatus(dto.getStatus());
        buku.setGenre(dto.getGenre());
        buku.setStock(dto.getStock());
        buku.setDateOfEntry(tanggal);

        Pengarang pengarang = pengarangRepository.findById(dto.getPengarang_id()).orElse(null);

        if (pengarang == null) {
            return ResponseEntity.badRequest().body("ID Pengarang tidak ditemukan!");
        }

        buku.setPengarang(pengarang);

        Penerbit penerbit = penerbitRepository.findById(dto.getPenerbit_id()).orElse(null);

        if (penerbit == null) {
            return ResponseEntity.badRequest().body("ID Penerbit tidak ditemukan!");
        }

        buku.setPenerbit(penerbit);

        Rak rak = rakRepository.findById(dto.getRak_id()).orElse(null);

        if (rak == null) {
            return ResponseEntity.badRequest().body("ID Rak tidak ditemukan!");
        }

        buku.setRak(rak);

        return ResponseEntity.ok(bukuRepository.save(buku));

    }

    @PutMapping("/edit-buku/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody BukuDto dto) {

        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggal = s.format(date);

        Buku buku = bukuRepository.findById(id).orElse(null);

        if (buku == null) {
            return ResponseEntity.badRequest().body("ID Buku tidak ditemukan!");
        }

        buku.setTitle(dto.getTitle());
        buku.setStatus(dto.getStatus());
        buku.setGenre(dto.getGenre());
        buku.setDateOfUpdate(tanggal);
        buku.setStock(dto.getStock());

        Pengarang pengarang = pengarangRepository.findById(dto.getPengarang_id()).orElse(null);

        if (pengarang == null) {
            return ResponseEntity.badRequest().body("ID Pengarang tidak ditemukan!");
        }

        buku.setPengarang(pengarang);

        Penerbit penerbit = penerbitRepository.findById(dto.getPenerbit_id()).orElse(null);

        if (penerbit == null) {
            return ResponseEntity.badRequest().body("ID Penerbit tidak ditemukan!");
        }

        buku.setPenerbit(penerbit);

        Rak rak = rakRepository.findById(dto.getRak_id()).orElse(null);

        if (rak == null) {
            return ResponseEntity.badRequest().body("ID Rak tidak ditemukan!");
        }

        buku.setRak(rak);

        return ResponseEntity.ok(bukuRepository.save(buku));

    }

    @DeleteMapping("/hapus-buku/{id}")
    public Object delete(@PathVariable("id") Integer id) {

        Buku buku = bukuRepository.findById(id).orElse(null);

        if (buku == null) {
            return ResponseEntity.badRequest().body("ID Buku tidak ditemukan!");
        }

        bukuRepository.delete(buku);

        return ResponseEntity.ok("Data buku berhasil dihapus");

    }

}

package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Denda;
import com.library.model.Pengembalian;
import com.library.repository.DendaRepository;
import com.library.repository.PengembalianRepository;

@RestController
@RequestMapping("/denda")
public class DendaController {
    
    DendaRepository dendaRepository;
    PengembalianRepository pengembalianRepository;

    @Autowired
    DendaController(DendaRepository dendaRepository, PengembalianRepository pengembalianRepository) {
        this.dendaRepository = dendaRepository;
        this.pengembalianRepository = pengembalianRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return dendaRepository.findAll();
    }

    @PostMapping("/pembayaran-denda/{id}")
    public Object insert(@PathVariable("id") Integer id, @RequestParam("uang") Integer uang) {
        Denda denda = dendaRepository.findById(id).orElse(null);
        Pengembalian pengembalian = pengembalianRepository.findById(id).orElse(null);

        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalMasuk = s.format(date);

        if (uang == denda.getPenalty().intValue()) {
            pengembalian.setDateOfEntry(tanggalMasuk);
            pengembalianRepository.save(pengembalian);

            denda.setStatus("SUDAH DIBAYAR");
            denda.setDateOfEntry(tanggalMasuk);
            dendaRepository.save(denda);

            return ResponseEntity.ok("Pembayaran Berhasil");
        } else if (uang > denda.getPenalty()) {
            pengembalian.setDateOfEntry(tanggalMasuk);
            pengembalianRepository.save(pengembalian);

            denda.setStatus("SUDAH DIBAYAR");
            denda.setDateOfEntry(tanggalMasuk);
            dendaRepository.save(denda);

            Integer kembalian = uang - denda.getPenalty();

            return ResponseEntity.ok("Pembayaran Berhasil\nKembalian anda Rp." + kembalian);
        } else {
            return ResponseEntity.badRequest().body("Maaf Uang anda kurang!");
        }
    }

}

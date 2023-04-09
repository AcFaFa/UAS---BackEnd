package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.model.Anggota;
import com.library.model.Buku;
import com.library.model.Denda;
import com.library.model.Pengembalian;
import com.library.repository.BukuRepository;
import com.library.repository.DendaRepository;
import com.library.repository.PengembalianRepository;

@RestController
@RequestMapping("/pengembalian")
public class PengembalianController {
    
    PengembalianRepository pengembalianRepository;
    BukuRepository bukuRepository;
    DendaRepository dendaRepository;

    @Autowired
    PengembalianController(PengembalianRepository pengembalianRepository, BukuRepository bukuRepository, DendaRepository dendaRepository) {
        this.pengembalianRepository = pengembalianRepository;
        this.bukuRepository = bukuRepository;
        this.dendaRepository = dendaRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return pengembalianRepository.findAll();
    }

    @PostMapping("/tambah-pengembalian/{id}")
    public Object insert(@PathVariable("id") Integer id) {

        Pengembalian pengembalian = pengembalianRepository.findById(id).orElse(null);
        Buku buku = pengembalian.getBuku();
        Anggota anggota = pengembalian.getAnggota();

        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalMasuk = s.format(date);
        String[] tampMasuk = tanggalMasuk.split("-");
        String[] tampPinjam = pengembalian.getReturnDate().toString().split("-");

        
        if (Integer.parseInt(tampMasuk[2]) > Integer.parseInt(tampPinjam[2])) {
            Integer penalty, lamaPinjam;
            lamaPinjam = Integer.parseInt(tampMasuk[2]) - Integer.parseInt(tampPinjam[2]);
            penalty = 10000 * lamaPinjam;

            Denda denda = new Denda();
            denda.setId(pengembalian.getId());
            denda.setReturnDate(pengembalian.getReturnDate());
            denda.setLengthOfLoan(lamaPinjam);
            denda.setPenalty(penalty);
            denda.setAnggota(anggota);
            denda.setBuku(buku);

            dendaRepository.save(denda);

            return ResponseEntity.ok("Anda melewati batas tanggal pengembalian\nAnda dikenai denda sebesar Rp." + penalty + "\nPengembalian buku tidak akan disahkan sebelum anda membayar denda tersebut!");
        }

        pengembalian.setDateOfEntry(tanggalMasuk);

        if (buku.getStock() == 0) {
            buku.setStatus("AVAILABLE");
        }

        buku.setStock(buku.getStock() + 1);

        bukuRepository.save(buku);

        return ResponseEntity.ok(pengembalianRepository.save(pengembalian));

    }

}

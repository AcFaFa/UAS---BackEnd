package com.library.controller;

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

import com.library.dto.AnggotaDto;
import com.library.model.Anggota;
import com.library.repository.AnggotaRepository;

@RestController
@RequestMapping("/anggota")
public class AnggotaController {

    AnggotaRepository anggotaRepository;

    @Autowired
    AnggotaController(AnggotaRepository anggotaRepository) {
        this.anggotaRepository = anggotaRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return anggotaRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Object findAnggota(@RequestParam("name") String name) {
        return anggotaRepository.findByName(name);
    }

    @PostMapping("/tambah-anggota")
    public Object insert(@RequestBody AnggotaDto dto) {

        if (anggotaRepository.findByName(dto.getName()) == null) {
            Anggota anggota = new Anggota();
            
            if (dto.getName().length() >= 0 && dto.getName().length() <= 50 && validasinama(dto.getName()) == 0) {
                anggota.setName(dto.getName());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }

            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                anggota.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
            anggota.setStatus(dto.getStatus());

            return ResponseEntity.ok(anggotaRepository.save(anggota));
        } else {
            return ResponseEntity.badRequest().body("Nama anggota sudah terdaftar!");
        }

    }

    @PutMapping("/edit-anggota/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody AnggotaDto dto) {

        Anggota anggota = anggotaRepository.findById(id).orElse(null);

        if (anggota == null) {
            return ResponseEntity.badRequest().body("ID Anggota tidak ditemukan");
        }

        if (anggotaRepository.findByName(dto.getName()) == null) {
            anggota.setName(dto.getName());
            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                anggota.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
            anggota.setStatus(dto.getStatus());

            return ResponseEntity.ok(anggotaRepository.save(anggota));
        } else {
            return ResponseEntity.badRequest().body("Nama anggota sudah terdaftar!");
        }

    }

    @DeleteMapping("/hapus-anggota/{id}")
    public Object delete(@PathVariable("id") Integer id) {

        Anggota anggota = anggotaRepository.findById(id).orElse(null);

        if (anggota == null) {
            return ResponseEntity.badRequest().body("ID Anggota tidak ditemukan!");
        }

        anggotaRepository.delete(anggota);

        return ResponseEntity.ok("Data anggota berhasil dihapus");

    }

    int validasinohp(String a) {
        int huruf = 0;
        int huruf2 = 0;
        if (a.charAt(0) == ' ' || a.charAt(a.length() - 1) == ' ' || a.charAt(2) == '6' || a.charAt(2) == '4'
                || a.charAt(2) == '0') {
            huruf2 = 1;
            return huruf2;
        }
        for (int x = 0; x < a.length(); x++) {
            if (!(a.charAt(x) >= '0' && a.charAt(x) <= '9' || a.charAt(0) != '+' || a.charAt(0) != '6'
                    || a.charAt(0) != '2' || a.charAt(0) != '8')) {
                huruf++;
            }
        }
        return huruf;
    }

    int validasinama(String a) {
        int huruf = 0;
        int huruf2 = 0;
        if (a.charAt(0) == ' ' || a.charAt(a.length() - 1) == ' ' || a.charAt(0) == '\''
                || a.charAt(a.length() - 1) == '\'' || a.charAt(0) == '-'
                || a.charAt(a.length() - 1) == '-') {
            huruf2 = 1;
            return huruf2;
        }
        for (int x = 0; x < a.length(); x++) {
            if (!(a.charAt(x) >= 'a' && a.charAt(x) <= 'z' || a.charAt(x) >= 'A' && a.charAt(x) <= 'Z'
                    || a.charAt(x) == '\'' && a.charAt(x + 1) != '\'' && a.charAt(x + 2) != '\''
                            && a.charAt(x + 1) != ' ' && a.charAt(x + 1) != '-'
                    || a.charAt(x) == ' ' && a.charAt(x + 1) != ' ' && a.charAt(x + 1) != '\'' && a.charAt(x + 1) != '-'
                            && a.charAt(x + 2) != ' '
                    || a.charAt(x) == '-' && a.charAt(x + 1) != '-' && a.charAt(x + 1) != ' ' && a.charAt(x + 1) != '\''
                            && a.charAt(x + 2) != '-')) {
                huruf++;
            }
        }
        return huruf;
    }

}

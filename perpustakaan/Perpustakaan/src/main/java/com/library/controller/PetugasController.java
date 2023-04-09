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

import com.library.dto.PetugasDto;
import com.library.model.Petugas;
import com.library.repository.PetugasRepository;

@RestController
@RequestMapping("/petugas")
public class PetugasController {
    
    PetugasRepository petugasRepository;

    @Autowired
    PetugasController(PetugasRepository petugasRepository) {
        this.petugasRepository = petugasRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return petugasRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Object findPetugas(@RequestParam("name") String name) {
        return petugasRepository.findByName(name);
    }

    @PostMapping("/tambah-petugas")
    public Object insert(@RequestBody PetugasDto dto) {
        
        if (petugasRepository.findByName(dto.getName()) == null) {
            Petugas petugas = new Petugas();
    
            if (dto.getName().length() >= 0 && dto.getName().length() <= 50 && validasinama(dto.getName()) == 0) {
                petugas.setName(dto.getName());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }

            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                petugas.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
    
            return ResponseEntity.ok(petugasRepository.save(petugas));
        } else {
            return ResponseEntity.badRequest().body("Nama petugas sudah terdaftar!");
        }
        
    }

    @PutMapping("/edit-petugas/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody PetugasDto dto) {
        Petugas petugas = petugasRepository.findById(id).orElse(null);

        if (petugas == null) {
            return ResponseEntity.badRequest().body("ID Petugas tidak ditemukan!");
        }

        if (petugasRepository.findByName(dto.getName()) == null) {
    
            if (dto.getName().length() >= 0 && dto.getName().length() <= 50 && validasinama(dto.getName()) == 0) {
                petugas.setName(dto.getName());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }

            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                petugas.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
    
            return ResponseEntity.ok(petugasRepository.save(petugas));
        } else {
            return ResponseEntity.badRequest().body("Nama petugas sudah terdaftar!");
        }
    }

    @DeleteMapping("/hapus-petugas/{id}")
    public Object delete(@PathVariable("id") Integer id) {
        Petugas petugas = petugasRepository.findById(id).orElse(null);

        if (petugas == null) {
            return ResponseEntity.badRequest().body("ID Petugas tidak ditemukan!");
        }

        petugasRepository.delete(petugas);

        return ResponseEntity.ok("Data petugas berhasil dihapus");
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

}

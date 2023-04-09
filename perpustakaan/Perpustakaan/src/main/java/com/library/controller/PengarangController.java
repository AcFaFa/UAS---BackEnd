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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.PengarangDto;
import com.library.model.Pengarang;
import com.library.repository.PengarangRepository;

@RestController
@RequestMapping("/pengarang")
public class PengarangController {

    PengarangRepository pengarangRepository;

    @Autowired
    PengarangController(PengarangRepository pengarangRepository) {
        this.pengarangRepository = pengarangRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return pengarangRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Object findPengarang(@RequestParam("name") String name) {
        return pengarangRepository.findByName(name);
    }

    @PostMapping("/tambah-pengarang")
    public Object insert(@RequestBody PengarangDto dto) {

        if (pengarangRepository.findByName(dto.getName()) == null) {
            Pengarang pengarang = new Pengarang();

            if (dto.getName().length() >= 0 && dto.getName().length() <= 50 && validasinama(dto.getName()) == 0) {
                pengarang.setName(dto.getName());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }

            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                pengarang.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
            pengarang.setAddress(dto.getAddress());

            return ResponseEntity.ok(pengarangRepository.save(pengarang));
        } else {
            return ResponseEntity.badRequest().body("Nama pengarang sudah terdaftar!");
        }

    }

    @PutMapping("/edit-pengarang/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody PengarangDto dto) {

        Pengarang pengarang = pengarangRepository.findById(id).orElse(null);

        if (pengarang == null) {
            return ResponseEntity.badRequest().body("ID Pengarang tidak ditemukan!");
        }

        if (pengarangRepository.findByName(dto.getName()) == null) {

            if (dto.getName().length() >= 0 && dto.getName().length() <= 50 && validasinama(dto.getName()) == 0) {
                pengarang.setName(dto.getName());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }

            if (dto.getPhoneNumber().length() >= 11 && dto.getPhoneNumber().length() <= 15
                    && validasinohp(dto.getPhoneNumber()) == 0) {
                pengarang.setPhoneNumber(dto.getPhoneNumber());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar!");
            }
            pengarang.setAddress(dto.getAddress());

            return ResponseEntity.ok(pengarangRepository.save(pengarang));
        } else {
            return ResponseEntity.badRequest().body("Nama pengarang sudah terdaftar!");
        }
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

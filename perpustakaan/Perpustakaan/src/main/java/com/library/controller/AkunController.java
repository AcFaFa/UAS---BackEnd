package com.library.controller;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.AkunDto;
import com.library.model.Akun;
import com.library.repository.AkunRepository;

@RestController
@RequestMapping("/akun")
public class AkunController {

    AkunRepository akunRepository;

    @Autowired
    AkunController(AkunRepository akunRepository) {
        this.akunRepository = akunRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return akunRepository.findAll();
    }

    @PostMapping("/tambah-akun")
    public Object insert(@RequestBody AkunDto dto) {

        if (akunRepository.validasiAkun(dto.getUsername()) == null) {
            Akun akun = new Akun();

            if (dto.getUsername().length() <= 30 && validasiUsername(dto.getUsername()) == 0) {
                akun.setUsername(dto.getUsername());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar");
            }

            if (dto.getPassword().length() <= 15 && dto.getPassword().length() >= 6 && validasiPassword(dto.getPassword()) == 0) {
                akun.setPassword(getMd5(dto.getPassword()));
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar");
            }

            akun.setLevel(dto.getLevel());

            return ResponseEntity.ok(akunRepository.save(akun));
        } else {
            return ResponseEntity.badRequest().body("Username sudah terdaftar");
        }

    }

    @PutMapping("/edit-akun/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody AkunDto dto) {

        Akun akun = akunRepository.findById(id).orElse(null);
        if (akun == null) {
            return ResponseEntity.badRequest().body("ID Akun tidak ditemukan!");
        }

        if (akunRepository.validasiAkun(dto.getUsername()) == null) {

            if (dto.getUsername().length() <= 30 && validasiUsername(dto.getUsername()) == 0) {
                akun.setUsername(dto.getUsername());
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar");
            }

            if (dto.getPassword().length() <= 15 && dto.getPassword().length() >= 6 && validasiPassword(dto.getPassword()) == 0) {
                akun.setPassword(getMd5(dto.getPassword()));
            } else {
                return ResponseEntity.badRequest().body("Masukan data dengan benar");
            }

            akun.setLevel(dto.getLevel());

            return ResponseEntity.ok(akunRepository.save(akun));
        } else {
            return ResponseEntity.badRequest().body("Username sudah terdaftar");
        }

    }

    @DeleteMapping("/hapus-akun/{id}")
    public Object delete(@PathVariable("id") Integer id) {

        Akun akun = akunRepository.findById(id).orElse(null);

        if (akun == null) {
            return ResponseEntity.badRequest().body("ID Akun tidak ditemukan!");
        }

        akunRepository.delete(akun);
        return ResponseEntity.ok("Data akun berhasil dihapus");

    }

    static int validasiUsername(String a) {
        int name = 0;
        int huruf2 = 0;
        if (a.charAt(0) == ' ' || a.charAt(a.length() - 1) == ' ' || a.charAt(0) == '_'
                || a.charAt(a.length() - 1) == '_') {
            huruf2 = 1;
            return huruf2;
        }
        for (int x = 0; x < a.length(); x++) {
            if (!(a.charAt(x) >= 'a' && a.charAt(x) <= 'z' || a.charAt(x) >= '0' && a.charAt(x) <= '9'
                    || a.charAt(x) == '_' && a.charAt(x + 1) != '_' || a.charAt(x) >= 'A' && a.charAt(x) <= 'Z')) {
                name++;
            }
        }
        return name;
    }

    static int validasiPassword(String a) {
        int pss = 0;
        int valpss2 = 0;
        if (a.charAt(0) == ' ' || a.charAt(a.length() - 1) == ' ') {
            valpss2 = 1;
            return valpss2;
        }
        for (int x = 0; x < a.length(); x++) {
            if (!(a.charAt(x) >= '0' && a.charAt(x) <= '9' || a.charAt(x) == ' ' || a.charAt(x) >= 'a' && a.charAt(x) <= 'z' || a.charAt(x) >= 'A' && a.charAt(x) <= 'Z')) {
                pss++;
            }
        }
        return pss;
    }

    public static String getMd5(String input)
    {
        try {
 
            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
 
            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());
 
            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);
 
            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
 
        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

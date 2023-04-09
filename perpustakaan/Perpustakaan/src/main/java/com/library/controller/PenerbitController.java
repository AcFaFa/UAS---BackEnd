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

import com.library.dto.PenerbitDto;
import com.library.model.Penerbit;
import com.library.repository.PenerbitRepository;

@RestController
@RequestMapping("/penerbit")
public class PenerbitController {

    PenerbitRepository penerbitRepository;

    @Autowired
    PenerbitController(PenerbitRepository penerbitRepository) {
        this.penerbitRepository = penerbitRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return penerbitRepository.findAll();
    }

    @GetMapping("/find-by-name")
    public Object findPenerbit(@RequestParam("name") String name) {
        return penerbitRepository.findByName(name);
    }

    @PostMapping("/tambah-penerbit")
    public Object insert(@RequestBody PenerbitDto dto) {

        Penerbit penerbit = new Penerbit();
        penerbit.setName(dto.getName());
        penerbit.setPhoneNumber(dto.getPhoneNumber());
        penerbit.setAddress(dto.getAddress());

        return ResponseEntity.ok(penerbitRepository.save(penerbit));

    }

    @PutMapping("/edit-penerbit/{id}")
    public Object update(@PathVariable("id") Integer id, @RequestBody PenerbitDto dto) {

        Penerbit penerbit = penerbitRepository.findById(id).orElse(null);

        if (penerbit == null) {
            return ResponseEntity.badRequest().body("ID Penerbit tidak ditemukan!");
        }

        penerbit.setName(dto.getName());
        penerbit.setPhoneNumber(dto.getPhoneNumber());
        penerbit.setAddress(dto.getAddress());

        return ResponseEntity.ok(penerbitRepository.save(penerbit));

    }

    // @DeleteMapping("/hapus-penerbit")
    // public Object delete(@PathVariable("id") Integer id) {

    // Penerbit penerbit = penerbitRepository.findById(id).orElse(null);

    // if (penerbit == null) {
    // return ResponseEntity.badRequest().body("ID Penerbit tidak ditemukan");
    // }

    // penerbitRepository.delete(penerbit);

    // return ResponseEntity.ok("Data penerbit berhasil dihapus");

    // }

}

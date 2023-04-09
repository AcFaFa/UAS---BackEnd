package com.library.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.dto.PeminjamanDto;
import com.library.model.Anggota;
import com.library.model.Buku;
import com.library.model.Peminjaman;
import com.library.model.Pengembalian;
import com.library.repository.AnggotaRepository;
import com.library.repository.BukuRepository;
import com.library.repository.PeminjamanRepository;
import com.library.repository.PengembalianRepository;

@RestController
@RequestMapping("/peminjaman")
public class PeminjamanController {
    
    PeminjamanRepository peminjamanRepository;
    PengembalianRepository pengembalianRepository;
    BukuRepository bukuRepository;
    AnggotaRepository anggotaRepository;

    @Autowired
    PeminjamanController(PeminjamanRepository peminjamanRepository, BukuRepository bukuRepository, AnggotaRepository anggotaRepository, PengembalianRepository pengembalianRepository) {
        this.peminjamanRepository = peminjamanRepository;
        this.pengembalianRepository = pengembalianRepository;
        this.bukuRepository = bukuRepository;
        this.anggotaRepository = anggotaRepository;
    }

    @GetMapping("/find-all")
    public Object findAll() {
        return peminjamanRepository.findAll();
    }

    @PostMapping("/tambah-peminjaman")
    public Object insert(@RequestBody PeminjamanDto dto) {

        Date date = new Date();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        String tanggalPinjam = s.format(date);
        String[] tamp = tanggalPinjam.split("-");
        String tanggalKembali;

        Integer hari, bulan, tahun, kabisat, dateCount;

        kabisat = Integer.parseInt(tamp[0]) % 4;

        dateCount = Integer.parseInt(tamp[2]) + 7;

        if (Integer.parseInt(tamp[1]) % 2 == 0) {

            hari = 30;

            if (Integer.parseInt(tamp[1]) == 2) {
                if (kabisat == 0) {
                    hari = 29;

                    if (dateCount <= hari) {
                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    } else {
                        dateCount -= hari;
                        tamp[1] = "03";

                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    }
                } else {
                    hari = 28;

                    if (dateCount <= hari) {
                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    } else {
                        dateCount -= hari;
                        tamp[1] = "03";
                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    }
                }
            } else {
                if (dateCount <= hari) {
                    if (dateCount.toString().length() == 2) {
                        tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                    } else {
                        tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                    }
                } else {
                    bulan = Integer.parseInt(tamp[1]) + 1;

                    if (bulan <= 12) {
                        hari = 30;
                        dateCount -= hari;

                        if (bulan.toString().length() != 2) {
                            tamp[1] = "0" + bulan;
                        } else {
                            tamp[1] = bulan.toString();
                        }

                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    } else {
                        hari = 31;
                        dateCount -= hari;

                        tahun = Integer.parseInt(tamp[0]) + 1;

                        tamp[1] = "01";
                        tamp[0] = tahun.toString();

                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    }
                }
            }

        } else {
            hari = 31;

            if (dateCount <= hari) {
                if (dateCount.toString().length() == 2) {
                    tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                } else {
                    tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                }
            } else {
                bulan = Integer.parseInt(tamp[1]) + 1;

                if (bulan == 2) {
                    if (kabisat == 0) {
                        hari = 31;
                        dateCount -= hari;

                        tamp[1] = "02";

                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    } else {
                        hari = 31;
                        dateCount -= hari;

                        tamp[1] = "02";

                        if (dateCount.toString().length() == 2) {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                        } else {
                            tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                        }
                    }
                } else {
                    hari = 31;
                    dateCount -= hari;

                    if (bulan.toString().length() != 2) {
                        tamp[1] = "0" + bulan;
                    } else {
                        tamp[1] = bulan.toString();
                    }

                    if (dateCount.toString().length() == 2) {
                        tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + dateCount;
                    } else {
                        tanggalKembali = tamp[0] + "-" + tamp[1] + "-" + "0" + dateCount;
                    }
                }
            }
        }

        Peminjaman peminjaman = new Peminjaman();
        peminjaman.setLoanDate(tanggalPinjam);
        peminjaman.setReturnDate(tanggalKembali);

        Pengembalian pengembalian = new Pengembalian();
        pengembalian.setReturnDate(tanggalKembali);
        
        Anggota anggota = anggotaRepository.findById(dto.getAnggota_id()).orElse(null);

        if (anggota == null) {
            return ResponseEntity.badRequest().body("ID Anggota tidak ditemukan!");
        }

        Buku buku = bukuRepository.findById(dto.getBuku_id()).orElse(null);

        if (buku == null) {
            return ResponseEntity.badRequest().body("ID Buku tidak ditemukan!");
        }

        peminjaman.setAnggota(anggota);
        peminjaman.setBuku(buku);
        pengembalian.setAnggota(anggota);
        pengembalian.setBuku(buku);

        if (buku.getStock() > 0) {
            buku.setStock(buku.getStock() - 1); 
            buku.setDateOfUpdate(tanggalPinjam);
        } else {
            return ResponseEntity.badRequest().body("Buku tidak tersedia!");
        }

        if (buku.getStock() == 0) {
            buku.setStatus("UNAVAILABLE");
        }

        bukuRepository.save(buku);
        pengembalianRepository.save(pengembalian);

        return ResponseEntity.ok(peminjamanRepository.save(peminjaman));

    }

}

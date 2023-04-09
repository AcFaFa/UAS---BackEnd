package com.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Pengembalian {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(columnDefinition = "DATE")
    private String dateOfEntry;

    @Column(columnDefinition = "DATE")
    private String returnDate;

    @OneToOne
    @JoinColumn(name = "anggota_id")
    private Anggota anggota;

    @OneToOne
    @JoinColumn(name = "buku_id")
    private Buku buku;

}

package com.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class Denda {
    
    @Id
    @Column
    private Integer id;

    @Column
    private String dateOfEntry;

    @Column
    private String returnDate;

    @Column
    private Integer lengthOfLoan;

    @Column
    private Integer penalty;

    @Column
    private String status = "BELUM DIBAYAR";

    @OneToOne
    @JoinColumn(name = "anggota_id")
    private Anggota anggota;

    @OneToOne
    @JoinColumn(name = "buku_id")
    private Buku buku;

}

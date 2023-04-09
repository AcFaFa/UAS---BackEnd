package com.library.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Buku {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 50)
    private String title;

    @Column(length = 20)
    private String status;

    @Column(length = 20)
    private String genre;

    @Column(length = 20)
    private String type;

    @Column
    private String dateOfEntry;

    @Column
    private String dateOfUpdate;

    @Column
    private Integer stock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pengarang_id", referencedColumnName = "id")
    private Pengarang pengarang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "penerbit_id", referencedColumnName = "id")
    private Penerbit penerbit;

    @OneToOne
    @JoinColumn(name = "rak_id", referencedColumnName = "id")
    private Rak rak;

}

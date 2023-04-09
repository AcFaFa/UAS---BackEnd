package com.library.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@SQLDelete(sql = "UPDATE anggota SET aktif = false WHERE id = ?")
@Where(clause = "aktif = true")
public class Anggota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 50)
    private String name;

    @Column(length = 15)
    private String phoneNumber;

    @Column(length = 20)
    private String status;

    @Column
    private boolean aktif = Boolean.TRUE;

}

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
@SQLDelete(sql = "UPDATE akun SET deleted = true WHERE id = ?")
@Where(clause = "deleted = false")
public class Akun {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(length = 30)
    private String username;

    @Column(length = 32)
    private String password;

    @Column(length = 5)
    private String level;

    @Column
    private boolean deleted = Boolean.FALSE;

}

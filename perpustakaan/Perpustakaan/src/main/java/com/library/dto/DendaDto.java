package com.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DendaDto {
    
    String dateOfEntry;
    String returnDate;
    Integer lengthOfLoan;
    Integer penalty;
    Integer anggota_id;
    Integer buku_id;

}

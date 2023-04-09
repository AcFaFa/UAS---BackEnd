package com.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BukuDto {
    
    String title;
    String status;
    String genre;
    Integer stock;
    Integer pengarang_id;
    Integer penerbit_id;
    Integer rak_id;

}

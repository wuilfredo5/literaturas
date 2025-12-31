// src/main/java/com/gutendex/model/dto/GutendexResponseDTO.java
package com.gutendex.literaturas.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class GutendexResponseDTO {
    
    private Integer count;
    private String next;
    private String previous;
    private List<BookResponseDTO> results;
}
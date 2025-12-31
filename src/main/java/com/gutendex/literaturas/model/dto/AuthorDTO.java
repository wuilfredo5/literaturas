// src/main/java/com/gutendex/model/dto/AuthorDTO.java
package com.gutendex.literaturas.model.dto;

import lombok.Data;

@Data
public class AuthorDTO {
    
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    
    // Constructor para convertir desde Entity
    public AuthorDTO(com.gutendex.model.entity.Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthYear = author.getBirthYear();
        this.deathYear = author.getDeathYear();
    }
}
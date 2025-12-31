// src/main/java/com/gutendex/model/dto/AuthorDTO.java
package com.gutendex.literaturas.model.dto;

import com.gutendex.literaturas.model.entity.Author;
import lombok.Data;

@Data
public class AuthorDTO {
    
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    
    // Constructor para convertir desde Entity
    public AuthorDTO(com.gutendex.literaturas.model.entity.Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthYear = author.getBirthYear();
        this.deathYear = author.getDeathYear();
    }


}
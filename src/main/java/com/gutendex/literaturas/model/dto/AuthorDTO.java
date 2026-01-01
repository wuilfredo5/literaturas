// src/main/java/com/gutendex/literaturas/model/dto/AuthorDTO.java
package com.gutendex.literaturas.model.dto;




public class AuthorDTO {

    private Long id;
    private String name;

    public Integer getDeathYear() {
        return deathYear;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    private Integer birthYear;
    private Integer deathYear;

    // Constructor para convertir desde Entity
    public AuthorDTO(com.gutendex.literaturas.model.entity.Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthYear = author.getBirthYear();
        this.deathYear = author.getDeathYear();
    }

    // Constructor vacío para Jackson
    public AuthorDTO() {}
}
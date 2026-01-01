// src/main/java/com/gutendex/model/dto/PersonDTO.java
package com.gutendex.literaturas.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;



public class PersonDTO {
    
    @JsonProperty("birth_year")
    private Integer birthYear;

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    @JsonProperty("death_year")
    private Integer deathYear;
    
    private String name;
}
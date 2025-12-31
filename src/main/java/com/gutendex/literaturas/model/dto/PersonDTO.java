// src/main/java/com/gutendex/model/dto/PersonDTO.java
package com.gutendex.literaturas.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonDTO {
    
    @JsonProperty("birth_year")
    private Integer birthYear;
    
    @JsonProperty("death_year")
    private Integer deathYear;
    
    private String name;
}
// src/main/java/com/gutendex/model/dto/BookRequestDTO.java
package com.gutendex.literaturas.model.dto;

import lombok.Data;

@Data
public class BookRequestDTO {
    
    private String title;
    private String author;
    private String language;
    private String topic;
    private Integer year;
}
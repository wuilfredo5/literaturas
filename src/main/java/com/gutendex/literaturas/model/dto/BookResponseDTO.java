// src/main/java/com/gutendex/model/dto/BookResponseDTO.java
package com.gutendex.literaturas.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class BookResponseDTO {
    
    private Long id;
    private String title;
    private List<String> subjects;
    private List<PersonDTO> authors;
    private List<String> summaries;
    private List<PersonDTO> translators;
    private List<String> bookshelves;
    private List<String> languages;
    private Boolean copyright;
    
    @JsonProperty("media_type")
    private String mediaType;
    
    private FormatDTO formats;
    
    @JsonProperty("download_count")
    private Long downloadCount;
}
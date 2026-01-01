// src/main/java/com/gutendex/literaturas/model/dto/GutendexResponseDTO.java
package com.gutendex.literaturas.model.dto;

import java.util.List;

public class GutendexResponseDTO {

    private Integer count;
    private String next;
    private String previous;
    private List<BookResponseDTO> results;

    // Constructores
    public GutendexResponseDTO() {}

    // Getters y Setters COMPLETOS
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {  // <- FALTABA ESTE MÉTODO
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<BookResponseDTO> getResults() {
        return results;
    }

    public void setResults(List<BookResponseDTO> results) {  // <- FALTABA ESTE MÉTODO
        this.results = results;
    }
}

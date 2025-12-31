// src/main/java/com/gutendex/model/dto/BookDTO.java
package com.gutendex.literaturas.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class BookDTO {
    
    private Long id;
    private Long gutenbergId;
    private String title;
    private List<String> subjects;
    private List<String> authors;
    private List<String> summaries;
    private List<String> translators;
    private List<String> bookshelves;
    private List<String> languages;
    private String mediaType;
    private Long downloadCount;
    
    // Constructor para convertir desde Entity
    public BookDTO(com.gutendex.model.entity.Book book) {
        this.id = book.getId();
        this.gutenbergId = book.getGutenbergId();
        this.title = book.getTitle();
        this.subjects = List.copyOf(book.getSubjects());
        this.authors = book.getAuthors().stream()
                .map(author -> author.getName())
                .toList();
        this.summaries = List.copyOf(book.getSummaries());
        this.translators = book.getTranslators().stream()
                .map(translator -> translator.getName())
                .toList();
        this.bookshelves = List.copyOf(book.getBookshelves());
        this.languages = List.copyOf(book.getLanguages());
        this.mediaType = book.getMediaType();
        this.downloadCount = book.getDownloadCount();
    }
}
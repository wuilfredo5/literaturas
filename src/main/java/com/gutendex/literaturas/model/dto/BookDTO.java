package com.gutendex.literaturas.model.dto;

import java.util.List;
import java.util.stream.Collectors;

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

    // Constructores
    public BookDTO() {}

    public BookDTO(com.gutendex.literaturas.model.entity.Book book) {
        this.id = book.getId();
        this.gutenbergId = book.getGutenbergId();
        this.title = book.getTitle();
        this.subjects = List.copyOf(book.getSubjects());
        this.authors = book.getAuthors().stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        this.summaries = List.copyOf(book.getSummaries());
        this.translators = book.getTranslators().stream()
                .map(translator -> translator.getName())
                .collect(Collectors.toList());
        this.bookshelves = List.copyOf(book.getBookshelves());
        this.languages = List.copyOf(book.getLanguages());
        this.mediaType = book.getMediaType();
        this.downloadCount = book.getDownloadCount();
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGutenbergId() { return gutenbergId; }
    public void setGutenbergId(Long gutenbergId) { this.gutenbergId = gutenbergId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    public List<String> getAuthors() { return authors; }
    public void setAuthors(List<String> authors) { this.authors = authors; }

    public List<String> getSummaries() { return summaries; }
    public void setSummaries(List<String> summaries) { this.summaries = summaries; }

    public List<String> getTranslators() { return translators; }
    public void setTranslators(List<String> translators) { this.translators = translators; }

    public List<String> getBookshelves() { return bookshelves; }
    public void setBookshelves(List<String> bookshelves) { this.bookshelves = bookshelves; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Long getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Long downloadCount) { this.downloadCount = downloadCount; }
}
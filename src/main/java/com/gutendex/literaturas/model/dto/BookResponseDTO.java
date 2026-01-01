// src/main/java/com/gutendex/literaturas/model/dto/BookResponseDTO.java
package com.gutendex.literaturas.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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

    // Constructores
    public BookResponseDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<String> getSubjects() { return subjects; }
    public void setSubjects(List<String> subjects) { this.subjects = subjects; }

    public List<PersonDTO> getAuthors() { return authors; }
    public void setAuthors(List<PersonDTO> authors) { this.authors = authors; }

    public List<String> getSummaries() { return summaries; }
    public void setSummaries(List<String> summaries) { this.summaries = summaries; }

    public List<PersonDTO> getTranslators() { return translators; }
    public void setTranslators(List<PersonDTO> translators) { this.translators = translators; }

    public List<String> getBookshelves() { return bookshelves; }
    public void setBookshelves(List<String> bookshelves) { this.bookshelves = bookshelves; }

    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public Boolean getCopyright() { return copyright; }
    public void setCopyright(Boolean copyright) { this.copyright = copyright; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public FormatDTO getFormats() { return formats; }
    public void setFormats(FormatDTO formats) { this.formats = formats; }

    public Long getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Long downloadCount) { this.downloadCount = downloadCount; }
}
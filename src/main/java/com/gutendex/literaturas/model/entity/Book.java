package com.gutendex.literaturas.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gutenberg_id", unique = true)
    private Long gutenbergId;

    @Column(nullable = false)
    private String title;

    @ElementCollection
    @CollectionTable(name = "book_subjects", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "subject")
    private Set<String> subjects = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "book_summaries", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "summary", length = 2000)
    private Set<String> summaries = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "book_bookshelves", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "bookshelf")
    private Set<String> bookshelves = new HashSet<>();

    @ElementCollection
    @CollectionTable(name = "book_languages", joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language")
    private Set<String> languages = new HashSet<>();

    @Column(name = "copyright")
    private Boolean copyright;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "download_count")
    private Long downloadCount;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "book_translators",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> translators = new HashSet<>();

    @Embedded
    private BookFormats formats;

    // Constructores
    public Book() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGutenbergId() { return gutenbergId; }
    public void setGutenbergId(Long gutenbergId) { this.gutenbergId = gutenbergId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<String> getSubjects() { return subjects; }
    public void setSubjects(Set<String> subjects) { this.subjects = subjects; }

    public Set<String> getSummaries() { return summaries; }
    public void setSummaries(Set<String> summaries) { this.summaries = summaries; }

    public Set<String> getBookshelves() { return bookshelves; }
    public void setBookshelves(Set<String> bookshelves) { this.bookshelves = bookshelves; }

    public Set<String> getLanguages() { return languages; }
    public void setLanguages(Set<String> languages) { this.languages = languages; }

    public Boolean getCopyright() { return copyright; }
    public void setCopyright(Boolean copyright) { this.copyright = copyright; }

    public String getMediaType() { return mediaType; }
    public void setMediaType(String mediaType) { this.mediaType = mediaType; }

    public Long getDownloadCount() { return downloadCount; }
    public void setDownloadCount(Long downloadCount) { this.downloadCount = downloadCount; }

    public Set<Author> getAuthors() { return authors; }
    public void setAuthors(Set<Author> authors) { this.authors = authors; }

    public Set<Author> getTranslators() { return translators; }
    public void setTranslators(Set<Author> translators) { this.translators = translators; }

    public BookFormats getFormats() { return formats; }
    public void setFormats(BookFormats formats) { this.formats = formats; }
}
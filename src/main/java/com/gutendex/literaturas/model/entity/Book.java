// src/main/java/com/gutendex/model/entity/Book.java
package com.gutendex.literaturas.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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
}
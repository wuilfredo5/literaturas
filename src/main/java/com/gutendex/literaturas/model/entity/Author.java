package com.gutendex.literaturas.model.entity;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gutenberg_id")
    private Long gutenbergId;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_year")
    private Integer birthYear;

    @Column(name = "death_year")
    private Integer deathYear;

    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @ManyToMany(mappedBy = "translators")
    private Set<Book> translatedBooks = new HashSet<>();

    // Constructores
    public Author() {}

    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getGutenbergId() { return gutenbergId; }
    public void setGutenbergId(Long gutenbergId) { this.gutenbergId = gutenbergId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }

    public Set<Book> getBooks() { return books; }
    public void setBooks(Set<Book> books) { this.books = books; }

    public Set<Book> getTranslatedBooks() { return translatedBooks; }
    public void setTranslatedBooks(Set<Book> translatedBooks) { this.translatedBooks = translatedBooks; }
}
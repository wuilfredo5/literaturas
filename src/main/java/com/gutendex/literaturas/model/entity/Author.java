// src/main/java/com/gutendex/model/entity/Author.java
package com.gutendex.literaturas.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<>();

    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "translators")
    private Set<Book> translatedBooks = new HashSet<>();

    // Constructor para crear desde DTO
    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }
}
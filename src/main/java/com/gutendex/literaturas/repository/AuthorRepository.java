package com.gutendex.literaturas.repository;

import com.gutendex.literaturas.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingIgnoreCase(String name);

    boolean existsByNameAndBirthYearAndDeathYear(String name, Integer birthYear, Integer deathYear);
}
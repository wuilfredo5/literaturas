// src/main/java/com/gutendex/repository/BookRepository.java
package com.gutendex.literaturas.repository;

import com.gutendex.literaturas.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    List<Book> findByTitleContainingIgnoreCase(String title);
    
    List<Book> findByLanguagesContaining(String language);
    
    boolean existsByGutenbergId(Long gutenbergId);
}
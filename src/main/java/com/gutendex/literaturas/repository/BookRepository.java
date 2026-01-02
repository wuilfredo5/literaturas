package com.gutendex.literaturas.repository;

import com.gutendex.literaturas.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    @Query("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors WHERE :language MEMBER OF b.languages")
    List<Book> findByLanguagesContaining(@Param("language") String language);

    boolean existsByGutenbergId(Long gutenbergId);
}

package com.gutendex.literaturas.repository;

import com.gutendex.literaturas.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByNameContainingIgnoreCase(String name);

    // Consulta simplificada - la lógica compleja la manejamos en el servicio
    @Query("SELECT a FROM Author a WHERE a.birthYear IS NOT NULL AND a.deathYear IS NOT NULL AND a.birthYear <= :year AND a.deathYear >= :year")
    List<Author> findAliveInYearWithKnownDates(@Param("year") Integer year);

    boolean existsByNameAndBirthYearAndDeathYear(String name, Integer birthYear, Integer deathYear);
}
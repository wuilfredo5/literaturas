// src/main/java/com/gutendex/service/AuthorService.java
package com.gutendex.literaturas.service;

import com.gutendex.literaturas.model.dto.AuthorDTO;
import com.gutendex.literaturas.model.dto.BookMapper;
import com.gutendex.literaturas.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;
    
    public AuthorService(AuthorRepository authorRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }
    
    /**
     * Obtiene todos los autores
     */
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca autores por nombre
     */
    public List<AuthorDTO> getAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene autores vivos en un año específico
     */
    public List<AuthorDTO> getAuthorsAliveInYear(Integer year) {
        return authorRepository.findAliveInYear(year).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Cuenta el número total de autores
     */
    public long countAuthors() {
        return authorRepository.count();
    }
}
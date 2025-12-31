// src/main/java/com/gutendex/service/BookService.java
package com.gutendex.literaturas.service;

import com.gutendex.literaturas.client.GutendexClient;
import com.gutendex.literaturas.model.dto.BookDTO;
import com.gutendex.literaturas.model.dto.BookMapper;
import com.gutendex.literaturas.model.dto.BookResponseDTO;
import com.gutendex.literaturas.model.dto.GutendexResponseDTO;
import com.gutendex.literaturas.model.entity.Author;
import com.gutendex.literaturas.model.entity.Book;
import com.gutendex.literaturas.repository.AuthorRepository;
import com.gutendex.literaturas.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GutendexClient gutendexClient;
    private final BookMapper bookMapper;
    
    public BookService(BookRepository bookRepository, 
                      AuthorRepository authorRepository,
                      GutendexClient gutendexClient,
                      BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.gutendexClient = gutendexClient;
        this.bookMapper = bookMapper;
    }
    
    /**
     * Busca libros en Gutendex API y los guarda en la base de datos
     */
    @Transactional
    public List<BookDTO> searchAndSaveBooksByTitle(String title) {
        GutendexResponseDTO response = gutendexClient.searchBooksByTitle(title);
        
        if (response == null || response.getResults() == null) {
            return List.of();
        }
        
        return response.getResults().stream()
                .filter(bookDto -> !bookRepository.existsByGutenbergId(bookDto.getId()))
                .map(this::saveBookFromDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Guarda un libro desde un DTO, manejando autores duplicados
     */
    @Transactional
    private Optional<Book> saveBookFromDTO(BookResponseDTO bookDto) {
        try {
            Book book = bookMapper.toEntity(bookDto);
            
            // Manejar autores existentes
            book.getAuthors().forEach(author -> {
                Author existingAuthor = findOrCreateAuthor(author);
                if (existingAuthor.getId() != null) {
                    author.setId(existingAuthor.getId());
                }
            });
            
            // Manejar traductores existentes
            book.getTranslators().forEach(translator -> {
                Author existingTranslator = findOrCreateAuthor(translator);
                if (existingTranslator.getId() != null) {
                    translator.setId(existingTranslator.getId());
                }
            });
            
            return Optional.of(bookRepository.save(book));
        } catch (Exception e) {
            System.err.println("Error al guardar libro: " + e.getMessage());
            return Optional.empty();
        }
    }
    
    /**
     * Busca un autor existente o crea uno nuevo
     */
    private Author findOrCreateAuthor(Author author) {
        return authorRepository
                .findByNameContainingIgnoreCase(author.getName())
                .stream()
                .filter(a -> a.getBirthYear() == author.getBirthYear())
                .filter(a -> a.getDeathYear() == author.getDeathYear())
                .findFirst()
                .orElseGet(() -> authorRepository.save(author));
    }
    
    /**
     * Obtiene todos los libros de la base de datos
     */
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Busca libros por idioma
     */
    public List<BookDTO> getBooksByLanguage(String language) {
        return bookRepository.findByLanguagesContaining(language).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtiene un libro por ID
     */
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO);
    }
}
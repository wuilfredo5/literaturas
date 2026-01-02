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

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
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

    @Transactional
    public List<BookDTO> searchAndSaveBooksByTitle(String title) {
        GutendexResponseDTO response = gutendexClient.searchBooksByTitle(title);

        if (response == null || response.getResults() == null) {
            return new ArrayList<>();
        }

        return response.getResults().stream()
                .filter(bookDto -> !bookRepository.existsByGutenbergId(bookDto.getId()))
                .map(this::saveBookFromDTO)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    private Optional<Book> saveBookFromDTO(BookResponseDTO bookDto) {
        try {
            Book book = bookMapper.toEntity(bookDto);

            // CORRECCIÓN: Manejar autores de forma correcta
            Set<Author> managedAuthors = new HashSet<>();
            if (book.getAuthors() != null) {
                for (Author author : book.getAuthors()) {
                    Author managedAuthor = findOrCreateAuthor(author);
                    managedAuthors.add(managedAuthor);
                }
                book.setAuthors(managedAuthors);
            }

            // CORRECCIÓN: Manejar traductores de forma correcta
            Set<Author> managedTranslators = new HashSet<>();
            if (book.getTranslators() != null) {
                for (Author translator : book.getTranslators()) {
                    Author managedTranslator = findOrCreateAuthor(translator);
                    managedTranslators.add(managedTranslator);
                }
                book.setTranslators(managedTranslators);
            }

            return Optional.of(bookRepository.save(book));
        } catch (Exception e) {
            System.err.println("Error al guardar libro: " + e.getMessage());
            e.printStackTrace();  // Para ver el stack trace completo
            return Optional.empty();
        }
    }

    private Author findOrCreateAuthor(Author author) {
        // Buscar por nombre y años
        List<Author> existingAuthors = authorRepository.findByNameContainingIgnoreCase(author.getName());

        for (Author existing : existingAuthors) {
            boolean sameBirth = Objects.equals(existing.getBirthYear(), author.getBirthYear());
            boolean sameDeath = Objects.equals(existing.getDeathYear(), author.getDeathYear());

            if (sameBirth && sameDeath) {
                return existing;  // Retornar autor existente (managed)
            }
        }

        // Si no existe, crear nuevo autor
        Author newAuthor = new Author(author.getName(), author.getBirthYear(), author.getDeathYear());
        return authorRepository.save(newAuthor);
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByLanguage(String language) {
        return bookRepository.findByLanguagesContaining(language).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<BookDTO> getBookById(Long id) {
        return bookRepository.findById(id)
                .map(bookMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();

        long totalBooks = bookRepository.count();
        long totalAuthors = authorRepository.count();

        stats.put("totalBooks", totalBooks);
        stats.put("totalAuthors", totalAuthors);

        // Libros por idioma
        Map<String, Long> booksByLanguage = new HashMap<>();
        List<Book> allBooks = bookRepository.findAll();
        for (Book book : allBooks) {
            for (String language : book.getLanguages()) {
                booksByLanguage.put(language, booksByLanguage.getOrDefault(language, 0L) + 1);
            }
        }
        stats.put("booksByLanguage", booksByLanguage);

        // Top 5 libros más descargados
        List<Book> allBooksSorted = allBooks.stream()
                .sorted((b1, b2) -> Long.compare(b2.getDownloadCount(), b1.getDownloadCount()))
                .limit(5)
                .collect(Collectors.toList());

        stats.put("topDownloaded", allBooksSorted.stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList()));

        return stats;
    }
}
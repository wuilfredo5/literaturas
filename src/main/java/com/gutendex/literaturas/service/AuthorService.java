package com.gutendex.literaturas.service;

import com.gutendex.literaturas.model.dto.AuthorDTO;
import com.gutendex.literaturas.model.dto.BookMapper;
import com.gutendex.literaturas.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    public AuthorService(AuthorRepository authorRepository, BookMapper bookMapper) {
        this.authorRepository = authorRepository;
        this.bookMapper = bookMapper;
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getUniqueAuthors() {
        return authorRepository.findAll().stream()
                .collect(Collectors.toMap(
                        author -> author.getName() + "_" + author.getBirthYear() + "_" + author.getDeathYear(),
                        author -> author,
                        (existing, replacement) -> existing))
                .values().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<AuthorDTO> getAuthorsAliveInYear(Integer year) {
        return authorRepository.findAliveInYear(year).stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public long countAuthors() {
        return authorRepository.count();
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getAuthorStatistics() {
        Map<String, Object> stats = new HashMap<>();

        long totalAuthors = authorRepository.count();
        long authorsWithBirthYear = authorRepository.findAll().stream()
                .filter(a -> a.getBirthYear() != null)
                .count();
        long authorsWithDeathYear = authorRepository.findAll().stream()
                .filter(a -> a.getDeathYear() != null)
                .count();

        stats.put("totalAuthors", totalAuthors);
        stats.put("authorsWithBirthYear", authorsWithBirthYear);
        stats.put("authorsWithDeathYear", authorsWithDeathYear);
        stats.put("authorsWithoutDates", totalAuthors - authorsWithBirthYear);

        return stats;
    }
}

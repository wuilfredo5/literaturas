// src/main/java/com/gutendex/model/dto/BookMapper.java
package com.gutendex.literaturas.model.dto;

import com.gutendex.literaturas.model.entity.Author;
import com.gutendex.literaturas.model.entity.Book;
import com.gutendex.literaturas.model.entity.BookFormats;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.stream.Collectors;

@Component
public class BookMapper {
    
    public Book toEntity(BookResponseDTO dto) {
        Book book = new Book();
        book.setGutenbergId(dto.getId());
        book.setTitle(dto.getTitle());
        book.setSubjects(new HashSet<>(dto.getSubjects()));
        book.setSummaries(new HashSet<>(dto.getSummaries()));
        book.setBookshelves(new HashSet<>(dto.getBookshelves()));
        book.setLanguages(new HashSet<>(dto.getLanguages()));
        book.setCopyright(dto.getCopyright());
        book.setMediaType(dto.getMediaType());
        book.setDownloadCount(dto.getDownloadCount());
        
        // Mapear autores
        if (dto.getAuthors() != null) {
            book.setAuthors(dto.getAuthors().stream()
                    .map(person -> new Author(
                            person.getName(),
                            person.getBirthYear(),
                            person.getDeathYear()))
                    .collect(Collectors.toSet()));
        }
        
        // Mapear traductores
        if (dto.getTranslators() != null) {
            book.setTranslators(dto.getTranslators().stream()
                    .map(person -> new Author(
                            person.getName(),
                            person.getBirthYear(),
                            person.getDeathYear()))
                    .collect(Collectors.toSet()));
        }
        
        // Mapear formatos
        if (dto.getFormats() != null) {
            BookFormats formats = new BookFormats();
            formats.setTextPlain(dto.getFormats().getTextPlain());
            formats.setTextHtml(dto.getFormats().getTextHtml());
            formats.setTextPlainUtf8(dto.getFormats().getTextPlainUtf8());
            formats.setApplicationEpub(dto.getFormats().getApplicationEpub());
            formats.setApplicationMobi(dto.getFormats().getApplicationMobi());
            formats.setApplicationRdf(dto.getFormats().getApplicationRdf());
            formats.setApplicationZip(dto.getFormats().getApplicationZip());
            formats.setImageJpeg(dto.getFormats().getImageJpeg());
            formats.setTextPlainUs(dto.getFormats().getTextPlainUs());
            book.setFormats(formats);
        }
        
        return book;
    }
    
    public BookDTO toDTO(Book book) {
        return new BookDTO(book);
    }
    
    public AuthorDTO toDTO(Author author) {
        return new AuthorDTO(author);
    }
}
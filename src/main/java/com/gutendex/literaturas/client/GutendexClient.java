// src/main/java/com/gutendex/client/GutendexClient.java
package com.gutendex.literaturas.client;

import com.gutendex.literaturas.model.dto.GutendexResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class GutendexClient {
    
    private final RestTemplate restTemplate;
    private final String apiUrl;
    
    public GutendexClient(@Value("${gutendex.api.url}") String apiUrl) {
        this.restTemplate = new RestTemplate();
        this.apiUrl = apiUrl;
    }
    
    /**
     * Busca libros por título
     */
    public GutendexResponseDTO searchBooksByTitle(String title) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("search", title)
                    .queryParam("format", "json")
                    .build()
                    .toUri();
            
            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("Error al buscar libros por título: " + e.getMessage());
            return new GutendexResponseDTO();
        }
    }
    
    /**
     * Busca libros por autor
     */
    public GutendexResponseDTO searchBooksByAuthor(String author) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("search", author)
                    .queryParam("format", "json")
                    .build()
                    .toUri();
            
            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("Error al buscar libros por autor: " + e.getMessage());
            return new GutendexResponseDTO();
        }
    }
    
    /**
     * Busca libros por idioma
     */
    public GutendexResponseDTO searchBooksByLanguage(String languageCode) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl)
                    .queryParam("languages", languageCode)
                    .queryParam("format", "json")
                    .build()
                    .toUri();
            
            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("Error al buscar libros por idioma: " + e.getMessage());
            return new GutendexResponseDTO();
        }
    }
    
    /**
     * Obtiene un libro específico por ID de Gutenberg
     */
    public GutendexResponseDTO getBookById(Long gutenbergId) {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(apiUrl + "/" + gutenbergId)
                    .queryParam("format", "json")
                    .build()
                    .toUri();
            
            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("Error al obtener libro por ID: " + e.getMessage());
            return new GutendexResponseDTO();
        }
    }
}
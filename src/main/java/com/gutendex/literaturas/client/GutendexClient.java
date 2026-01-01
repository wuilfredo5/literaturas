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
     * Busca libros por título - VERSIÓN COMPATIBLE
     */
    public GutendexResponseDTO searchBooksByTitle(String title) {
        try {
            // VERSIÓN COMPATIBLE CON SPRING BOOT 3.x y 4.x
            URI uri = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("search", title.trim())
                    .build()
                    .toUri();

           // System.out.println("🔍 Buscando en URL: " + uri.toString());
            return restTemplate.getForObject(uri, GutendexResponseDTO.class);

        } catch (Exception e) {
            System.err.println("❌ Error al buscar libros por título: " + e.getMessage());
            e.printStackTrace();
            return createEmptyResponse();
        }
    }

    /**
     * Busca libros por autor
     */
    public GutendexResponseDTO searchBooksByAuthor(String author) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("search", author.trim())
                    .build()
                    .toUri();

            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("❌ Error al buscar libros por autor: " + e.getMessage());
            return createEmptyResponse();
        }
    }

    /**
     * Busca libros por idioma
     */
    public GutendexResponseDTO searchBooksByLanguage(String languageCode) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(apiUrl)
                    .queryParam("languages", languageCode.trim().toLowerCase())
                    .build()
                    .toUri();

            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("❌ Error al buscar libros por idioma: " + e.getMessage());
            return createEmptyResponse();
        }
    }

    /**
     * Obtiene un libro específico por ID de Gutenberg
     */
    public GutendexResponseDTO getBookById(Long gutenbergId) {
        try {
            URI uri = UriComponentsBuilder.fromUriString(apiUrl + "/" + gutenbergId)
                    .build()
                    .toUri();

            return restTemplate.getForObject(uri, GutendexResponseDTO.class);
        } catch (Exception e) {
            System.err.println("❌ Error al obtener libro por ID: " + e.getMessage());
            return createEmptyResponse();
        }
    }

    /**
     * Crea una respuesta vacía para manejar errores
     */
    private GutendexResponseDTO createEmptyResponse() {
        GutendexResponseDTO emptyResponse = new GutendexResponseDTO();
        emptyResponse.setCount(0);
        emptyResponse.setResults(java.util.Collections.emptyList());
        return emptyResponse;

    }

}
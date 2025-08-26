package com.generator.service;

import com.generator.model.BookDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SmartLibraryClient {
    private final RestTemplate restTemplate;
    @Value("${smartlibrary.api.url}")
    private String baseUrl;

    public SmartLibraryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean isIsbnUnique(String isbn) {
        String url = baseUrl + "/books/isbn/isbn=" + isbn;
        try {
            ResponseEntity<BookDto[]> response = restTemplate.getForEntity(url, BookDto[].class);
            return response.getBody() == null || response.getBody().length == 0;
        } catch (HttpClientErrorException.NotFound e) {
            return true;
        } catch (RestClientException e) {
            System.err.println("Error checking ISBN: " + e.getMessage());
            return false;
        }
    }

    public void addBook(BookDto book) {
        String url = baseUrl + "/books";
        restTemplate.postForEntity(url, book, BookDto.class);
    }
}


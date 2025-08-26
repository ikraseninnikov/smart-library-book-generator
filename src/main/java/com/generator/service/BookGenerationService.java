package com.generator.service;

import com.generator.model.BookDto;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class BookGenerationService {
    private final SmartLibraryClient smartLibraryClient;
    private final Random random = new Random();

    private static final Logger logger = LoggerFactory.getLogger(BookGenerationService.class);

    public BookGenerationService(SmartLibraryClient smartLibraryClient) {
        this.smartLibraryClient = smartLibraryClient;
    }

    @Scheduled(fixedRate = 60000)
    public void generateAndSendBook() {
        int maxAttempts = 10;
        BookDto book;
        int attempts = 0;

        do {
            book = generateRandomBook();
            attempts++;
            if (attempts > maxAttempts) {
                logger.warn("Can't generate unique ISBN after {} attempts", maxAttempts);
                return;
            }
        } while (!smartLibraryClient.isIsbnUnique(book.getIsbn()));

        smartLibraryClient.addBook(book);
        logger.info("The book is generated and send: {}", book);
    }

    private BookDto generateRandomBook() {
        String title = "Book " + UUID.randomUUID().toString().substring(0, 8);
        String author = "Author " + UUID.randomUUID().toString().substring(0, 5);
        String isbn = "ISBN-" + (100000 + random.nextInt(900000));
        boolean available = true;

        return new BookDto(null, title, author, isbn, available);
    }
}


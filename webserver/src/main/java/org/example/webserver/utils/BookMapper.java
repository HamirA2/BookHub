package org.example.webserver.utils;

import org.example.webserver.dto.AdminBookDto;
import org.example.webserver.dto.BookDto;
import org.example.webserver.entities.Book;

public class BookMapper {
    private BookMapper() {}

    public static BookDto bookToBookDto(Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getRating()
        );
    }

    public static AdminBookDto bookToAdminDto(Book book) {
        return new AdminBookDto(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getRating(),
                book.getPrice(),
                book.getPublishDate()
        );
    }
}

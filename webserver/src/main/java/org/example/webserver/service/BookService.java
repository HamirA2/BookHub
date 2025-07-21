package org.example.webserver.service;

import org.example.webserver.dto.BookDto;
import org.example.webserver.dto.PostNewBook;

import java.util.List;

public interface BookService<T extends BookDto, P extends PostNewBook> {
    List<T> getAllBooks();
    List<T> getAllBooksByAuthor(String author);
    List<T> getAllBooksByGenre(String genre);
    T getBookByTitle(String title);
    T getBookById(int id);
    T createBook(P book);
    T updateRating(int bookId, byte rating);
    T updateBook(T book);
    void deleteBookById(int bookId);
}

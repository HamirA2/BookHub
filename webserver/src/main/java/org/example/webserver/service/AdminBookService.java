package org.example.webserver.service;

import org.example.webserver.dto.AdminBookDto;
import org.example.webserver.dto.PostNewBookAdmin;

import java.util.List;

public interface AdminBookService {
    List<AdminBookDto> getAllBooks();
    List<AdminBookDto> getAllBooksByAuthor(String author);
    List<AdminBookDto> getAllBooksByGenre(String genre);
    AdminBookDto getBookByTitle(String title);
    AdminBookDto getBookById(int id);
    AdminBookDto createBook(PostNewBookAdmin book);
    AdminBookDto updateBook(AdminBookDto book);
    AdminBookDto updateRating(int bookId, byte rating);
    void deleteBookById(int id);
}

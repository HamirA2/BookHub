package org.example.webserver.service;

import org.example.webserver.dto.AdminBookDto;
import org.example.webserver.dto.BookDto;
import org.example.webserver.dto.PostNewBook;
import org.example.webserver.dto.PostNewBookAdmin;
import org.example.webserver.entities.Book;
import org.example.webserver.exceptions.BookNotFoundException;
import org.example.webserver.repositories.BookRepository;
import org.example.webserver.utils.BookMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("admin")
public class AdminBookServiceImpl implements BookService<AdminBookDto, PostNewBookAdmin> {
    private final BookRepository bookRepository;

    public AdminBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<AdminBookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> {
                    return BookMapper.bookToAdminDto(book);
                })
                .toList();
    }

    @Override
    public List<AdminBookDto> getAllBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author).stream()
                .map(book -> {
                    return BookMapper.bookToAdminDto(book);
                })
                .toList();
    }

    @Override
    public List<AdminBookDto> getAllBooksByGenre(String genre) {
        return bookRepository.findAllByGenreIgnoreCase(genre).stream()
                .map(book -> {
                    return BookMapper.bookToAdminDto(book);
                })
                .toList();
    }

    @Override
    public AdminBookDto getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book with title ".concat(title)
                        .concat(" not found!")));
        return BookMapper.bookToAdminDto(book);
    }

    @Override
    public AdminBookDto getBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        return BookMapper.bookToAdminDto(book);
    }

    @Override
    public AdminBookDto createBook(PostNewBookAdmin book) {
        Book newBook = bookRepository.save(new Book(
                0,
                book.getTitle(),
                book.getAuthor(),
                book.getPublishDate(),
                book.getGenre(),
                book.getRating(),
                book.getPrice())
        );
        return BookMapper.bookToAdminDto(newBook);
    }

    @Override
    public AdminBookDto updateRating(int bookId, byte rating) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " not found!"));
        book.setRating(rating);
        book = bookRepository.save(book);
        return BookMapper.bookToAdminDto(book);
    }

    @Override
    public AdminBookDto updateBook(AdminBookDto book) {
        Book oldBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new BookNotFoundException("Book with id " + book.getId() + " not found!"));
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setGenre(book.getGenre());
        oldBook.setRating(book.getRating());
        oldBook.setPrice(book.getPrice());
        oldBook = bookRepository.save(oldBook);
        return BookMapper.bookToAdminDto(oldBook);
    }

    @Override
    public void deleteBookById(int bookId) {
        bookRepository.deleteById(bookId);
    }
}

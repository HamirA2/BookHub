package org.example.webserver.service;

import org.example.webserver.dto.BookDto;
import org.example.webserver.dto.PostNewBook;
import org.example.webserver.entities.Book;
import org.example.webserver.exceptions.BookNotFoundException;
import org.example.webserver.repositories.BookRepository;
import org.example.webserver.utils.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class UserBookServiceImpl implements BookService<BookDto, PostNewBook> {

    private final BookRepository bookRepository;

    public UserBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> {
                   return BookMapper.bookToBookDto(book);
                })
                .toList();
    }

    @Override
    public List<BookDto> getAllBooksByAuthor(String author) {
        return bookRepository.findAllByAuthor(author).stream()
                .map(book -> {
                    return BookMapper.bookToBookDto(book);
                })
                .toList();
    }

    @Override
    public List<BookDto> getAllBooksByGenre(String genre) {
        return bookRepository.findAllByGenreIgnoreCase(genre).stream()
                .map(book -> {
                    return BookMapper.bookToBookDto(book);
                })
                .toList();
    }

    @Override
    public BookDto getBookByTitle(String title) {
        Book book = bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book with title ".concat(title)
                        .concat(" not found!")));
        return BookMapper.bookToBookDto(book);
    }

    @Override
    public BookDto getBookById(int id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + id + " not found!"));
        return BookMapper.bookToBookDto(book);
    }

    @Override
    public BookDto createBook(PostNewBook book) {
        Book newBook = bookRepository.save(new Book(
                0,
                book.getTitle(),
                book.getAuthor(),
                book.getPublishDate(),
                book.getGenre(),
                book.getRating()));
        return BookMapper.bookToBookDto(newBook);
    }

    @Override
    public BookDto updateRating(int bookId, byte rating) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " not found!"));
        book.setRating(rating);
        book = bookRepository.save(book);
        return BookMapper.bookToBookDto(book);
    }

    @Override
    public BookDto updateBook(BookDto book) {
        Book oldBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new BookNotFoundException("Book with id " + book.getId() + " not found!"));
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setGenre(book.getGenre());
        oldBook.setRating(book.getRating());
        oldBook = bookRepository.save(oldBook);
        return BookMapper.bookToBookDto(oldBook);
    }

    @Override
    public void deleteBookById(int bookId) {
        bookRepository.deleteById(bookId);
    }
}

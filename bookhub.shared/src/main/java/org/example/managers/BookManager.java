package org.example.managers;

import org.example.entities.Book;
import org.example.exceptions.BookNotFoundException;
import org.example.utils.BookFileHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class BookManager {
    private List<Book> books;
    private boolean currentUpdateTracker = false;
    private ExecutorService executorService;

    {
        executorService = Executors.newSingleThreadExecutor();
        books = BookFileHandler.loadBooks();
        executorService.submit(autoSaveRunnable());
    }

    public BookManager() {}

    public void addBook(String title,
                        String author,
                        LocalDate publishDate,
                        float price,
                        String genre,
                        byte rating) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishDate(publishDate);
        book.setPrice(price);
        book.setGenre(genre);
        book.setRating(rating);
        book.displayBook();
        books.add(book);
        currentUpdateTracker = true;
    }

    public void addBook(String title,
                        String author,
                        LocalDate publishDate,
                        String genre) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishDate(publishDate);
        book.setPrice(0.0f);
        book.setGenre(genre);
        book.setRating((byte)0);
        book.displayBook();
        books.add(book);
        currentUpdateTracker = true;
    }

    public void addBook(String title,
                        String author,
                        LocalDate publishDate,
                        String genre,
                        byte rating) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setPublishDate(publishDate);
        book.setPrice(0.0f);
        book.setGenre(genre);
        book.setRating(rating);
        book.displayBook();
        books.add(book);
        currentUpdateTracker = true;
    }

    public List<Book> getAllBooks() {
        return books;
    }

    public Book getBookById(int bookId) {
        return books.stream()
                .filter(book -> book.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book not found!"));
    }

    public Book getBookByTitle(String title) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                return books.get(i);
            }
        }
        return null;
    }

    public List<Book> getBooksByAuthor(String author) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author) ||
                book.getAuthor().contains(author)) {
                booksByAuthor.add(book);
            }
        }
        return booksByAuthor;
    }

    // Stream api functions below
    public List<Book> getBooksByGenre(String genre) {
        return books.stream() // converts the collection into a stream object
                // intermediary operation(process operations)
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .toList(); // terminal operator(ends and converts)
    }

    public Map<String, Long> getBookGenreStatistics() {
        return books.stream()
                .collect(Collectors.groupingBy(
                        Book::getGenre,
                        Collectors.counting()));
    }

    public List<Book> getSortedBooksByPublishDate() {
        return books.stream()
                .sorted((book1, book2)
                        -> book1.getPublishDate().compareTo(book2.getPublishDate()))
                .toList();
    }

    public boolean updateBook(Book book) {
        Book oldBook = books.stream()
                .filter(b1 -> b1.getId() == book.getId())
                .findAny()
                .orElseThrow(() -> new BookNotFoundException("Book was not found!"));
        if(oldBook.equals(book)) {
            return false;
        }
        books.replaceAll(b -> b.getId() == book.getId() ? book : b);
        currentUpdateTracker = true;
        return true;
    }

    public boolean updateRating(int bookId, byte rating) {
        Book book = books.stream()
                .filter(b -> b.getId() == bookId)
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException("Book was not found!"));
        book.setRating(rating);
        currentUpdateTracker = true;
        return true;
    }

    public boolean deleteBookById(int bookId) {
        try {
            return books.removeIf(b -> b.getId() == bookId);
        } finally {
            currentUpdateTracker = true;
        }

    }

    public Runnable autoSaveRunnable() {
        Runnable runnable = () -> {
            while (true) {
                if (currentUpdateTracker) {
                    BookFileHandler.saveBooks(books);
                    currentUpdateTracker = false;
                }
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.err.println("Threading Error has occurred: " + e.getMessage());
                }

            }
        };

        return runnable;
    }
}

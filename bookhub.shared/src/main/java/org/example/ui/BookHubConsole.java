package org.example.ui;

import org.example.entities.Book;
import org.example.managers.BookManager;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class BookHubConsole {
    private BookManager bookManager;
    private Scanner scanner;

    public BookHubConsole() {
        bookManager = new BookManager();
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to BookHub");

        while (true) {
            displayMenu();
            int choice = scanner.nextInt();

            // DO THE REST OF THE MENU!!!!
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    getAllBooks();
                    break;
                case 3:
                    getBookByTitle();
                    break;
                case 4:
                    getBooksByAuthor();
                    break;
                case 5:
                    getBooksByGenre();
                    break;
                case 6:
                    getBookGenreStats();
                    break;
                case 7:
                    getSortedBookList();
                    break;
                case 8:
                    updateBook();
                    break;
                case 10:
                    deleteById();
                    break;
                case 11:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }

    private void displayMenu() {
        System.out.println("""
                +-------------------------------+
                |           Main Menu           |
                +-------------------------------+
                |1. Add a Book                  |
                |2. Get all Books               |
                |3. Get Book by Title           |
                |4. Get Books by Author         |
                |5. Get Books by Genre          |
                |6. Get Book Genre Stats        |
                |7. Get Sorted Book List        |
                |8. Update a Book               |
                |9. Update Rating of Book       |
                |10. Delete a Book              |
                |11. Exit                       |
                +-------------------------------+
                """); // "" Single line, // """ for multi line
    }

    private void displayBookIds() {
        System.out.println("Current Book Ids");
        bookManager.getAllBooks().forEach(book -> {
            // REVIEW
            System.out.printf("Id: %d, Title: %s", book.getId(), book.getTitle());
        });
    }

    private void addBook() {
        scanner.nextLine();
        System.out.println("Enter Title: ");
        String titleString = scanner.nextLine();

        System.out.println("Enter Author: ");
        String authorString = scanner.nextLine();

        System.out.println("Enter Publish Date(YYYY-MM-DD): ");
        LocalDate publishDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter Genre: ");
        String genreString = scanner.nextLine();

        System.out.println("Enter Rating(optional): ");
        String ratingOptional = scanner.nextLine();

        System.out.println("Enter Price(optional): ");
        String priceOptional = scanner.nextLine();

        if (!ratingOptional.isBlank()) {
            if (!priceOptional.isBlank()) {
                bookManager.addBook(
                        titleString,
                        authorString,
                        publishDate,
                        Float.parseFloat(priceOptional),
                        genreString,
                        Byte.parseByte(ratingOptional));
                return;
            }
            bookManager.addBook(
                    titleString,
                    authorString,
                    publishDate,
                    genreString,
                    Byte.parseByte(ratingOptional));
            return;
        }

        bookManager.addBook(
                titleString,
                authorString,
                publishDate,
                genreString);
    }

    private void getAllBooks() {
        if (bookManager.getAllBooks().isEmpty()) {
            System.out.println("The library is empty!");
            return;
        }

        System.out.println("Your Books:");
        bookManager.getAllBooks().forEach(Book::displayBook);
    }

    private void getBookByTitle() {
        scanner.nextLine();
        displayBookIds();
        System.out.println("\nPlease choose a book title:");
        String userTitle = scanner.nextLine();

        if (userTitle.isBlank()) userTitle = "";

        Book getBook = bookManager.getBookByTitle(userTitle);

        if (getBook != null) {
            getBook.displayBook();
        } else {
            System.out.println("No Book found with that title!");
        }

    }

    private void getBooksByAuthor() {
        scanner.nextLine();
        bookManager.getAllBooks().forEach(Book::displayBook);
        System.out.println("Please choose an author:");
        String authorChoice = scanner.nextLine();

        if (authorChoice.isBlank()) authorChoice = "bndk;gd*";


        List<Book> booksByAuthor = bookManager.getBooksByAuthor(authorChoice);

        if (booksByAuthor.isEmpty()) {
            System.out.println("No books found by that author!");
        } else {
            booksByAuthor.forEach(Book::displayBook);
        }
    }

    private void getBooksByGenre() {
        scanner.nextLine();
        bookManager.getAllBooks().forEach(Book::displayBook);
        System.out.println("Please choose a book genre:");
        String genreChoice = scanner.nextLine();

        if (genreChoice.isBlank()) genreChoice = "1#*%";

        List<Book> booksByGenre = bookManager.getBooksByGenre(genreChoice);

        if (booksByGenre.isEmpty()) {
            System.out.println("No books found by that genre!");
        } else {
            booksByGenre.forEach(Book::displayBook);
        }
    }

    private void getBookGenreStats() {
        System.out.println("Current book genres:");
        Map<String, Long> genreStats = bookManager.getBookGenreStatistics();

        if (genreStats.isEmpty()) {
            System.out.println("There are no genres currently!");
        } else {
            System.out.println(genreStats);
        }
    }

    private void getSortedBookList() {
        System.out.println("All books sorted by date:");
        List<Book> sortedBooks = bookManager.getSortedBooksByPublishDate();

        if (sortedBooks.isEmpty()) {
            System.out.println("No books are available for sorting!");
        } else {
            sortedBooks.forEach(Book::displayBook);
        }
    }

    // COMPLETE UPDATE BY RATING
    private void updateBookRating() {

    }

    private void updateBook() {
        displayBookIds();
        System.out.println("Please choose an Id: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        try {
            Book existingBook = bookManager.getBookById(choice);
            System.out.println("Current Book Information");
            existingBook.displayBook();

            System.out.println("Enter new title (current: "
                    .concat(existingBook.getTitle())
                    .concat(" ): "));
            String newTitle = scanner.nextLine();

            if (newTitle.isBlank()) newTitle = existingBook.getTitle();

            System.out.println("Enter new author (current: "
                    .concat(existingBook.getAuthor())
                    .concat(" ): "));
            String newAuthor = scanner.nextLine();

            if (newAuthor.isBlank()) newAuthor = existingBook.getAuthor();

            System.out.println("Enter new published date (current: "
                    .concat(existingBook.getPublishDate().toString())
                    .concat(" ) [YYYY-MM-DD]: "));
            String dateInput = scanner.nextLine();
            LocalDate newDate = dateInput.isBlank() ? existingBook.getPublishDate() : LocalDate.parse(dateInput);

            System.out.println("Enter a new genre (current: "
                    .concat(existingBook.getGenre())
                    .concat(" ): "));
            String newGenre = scanner.nextLine();

            if (newGenre.isBlank()) newGenre = existingBook.getGenre();

            System.out.println("Enter new price (current: "
                    + (existingBook.getPrice()) + " ): ");
            String priceInput = scanner.nextLine();
            float newPrice = priceInput.isBlank() ? existingBook.getPrice() : Float.parseFloat(priceInput);

            Book updatedBook = new Book(existingBook.getId(), existingBook.getDateAdded());

            updatedBook.setTitle(newTitle);
            updatedBook.setAuthor(newAuthor);
            updatedBook.setPublishDate(newDate);
            updatedBook.setGenre(newGenre);
            updatedBook.setPrice(newPrice);
            updatedBook.setRating(existingBook.getRating());

            if (bookManager.updateBook(updatedBook)) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("No changes made.");
            }
        } catch (Exception e) {
            System.err.println("Book not found or invalid input!");
        }


    }

    private void deleteById() {
        displayBookIds();
        System.out.println("Please choose an Id: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        if (bookManager.deleteBookById(choice)) {
            System.out.println("Book successfully deleted!");
        } else {
            System.err.println("Invalid Book Choice!");
        }
    }
}

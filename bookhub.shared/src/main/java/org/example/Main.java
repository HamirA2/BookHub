package org.example;

import org.example.entities.Book;
import org.example.managers.BookManager;
import org.example.ui.BookHubConsole;
import org.example.utils.BookFileHandler;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        BookManager bookManager = new BookManager();
        BookHubConsole console = new BookHubConsole();

        console.start();

//        List<Book> books = new ArrayList<Book>();
////
//        books.add(new Book("The Hobbit",
//                "J.R.R. Tolkien",
//                LocalDate.of(1937, 9, 21),
//                15.99f,
//                "Fantasy",
//                (byte) 5));
//        books.add(new Book("1984",
//                "George Orwell",
//                LocalDate.of(1949, 6, 8), 12.50f,
//                "Dystopian",
//                (byte) 4));
//        books.add(new Book("To Kill a Mockingbird",
//                "Harper Lee",
//                LocalDate.of(1960, 7, 11), 10.00f,
//                "Classic",
//                (byte) 5));
//        books.add(new Book("The Great Gatsby",
//                "F. Scott Fitzgerald",
//                LocalDate.of(1925, 4, 10),
//                14.99f,
//                "Classic",
//                (byte) 3));
//        books.add(new Book("Dune",
//                "Frank Herbert",
//                LocalDate.of(1965, 8, 1),
//                18.75f,
//                "Science Fiction",
//                (byte) 5));
//        books.add(new Book("Brave New World",
//                "Aldous Huxley",
//                LocalDate.of(1932, 1, 1),
//                13.45f,
//                "Dystopian",
//                (byte) 4));
//        books.add(new Book("The Catcher in the Rye",
//                "J.D. Salinger",
//                LocalDate.of(1951, 7, 16),
//                9.99f,
//                "Fiction",
//                (byte) 3));
//        books.add(new Book("Moby-Dick",
//                "Herman Melville",
//                LocalDate.of(1851, 10, 18), 11.50f,
//                "Adventure",
//                (byte) 2));
//        books.add(new Book("Frankenstein",
//                "Mary Shelley",
//                LocalDate.of(1818, 1, 1),
//                10.99f,
//                "Horror",
//                (byte) 4));
//        books.add(new Book("The Road",
//                "Cormac McCarthy",
//                LocalDate.of(2006, 9, 26),
//                16.00f,
//                "Post-Apocalyptic",
//                (byte) 4));
//
//        BookFileHandler.saveBooks(books);
//

    }
}
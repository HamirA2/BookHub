package org.example;

import org.example.entities.Book;
import org.example.managers.BookManager;
import org.example.ui.BookHubConsole;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookManager bookManager = new BookManager();
        BookHubConsole console = new BookHubConsole();
//        System.out.println("Beginning of add book");
//        bookManager.addBook("Gantz",
//                "Hiroya Oku",
//                LocalDate.of(2000, 6, 1),
//                "Manga");
//        System.out.println("Book added");
//        bookManager.addBook("Clean Code",
//                "Robert C. Martin",
//                LocalDate.of(2008, 8, 1),
//                "Non Fiction",
//                (byte)4);
//        System.out.println("Book added");
//
//        System.out.println(bookManager.getBookGenreStatistics());

//        Book book1 = new Book();
//        Book book2 = new Book("Adventures of Huckleberry Finn",
//                "Mark Twain",
//                LocalDate.of(1884, 12, 10),
//                15.00f,
//                "Fiction",
//                (byte) 5);
//        Book book3 = new Book("The Martian",
//                "Andy Weir",
//                LocalDate.of(2014, 10, 28),
//                9.00f,
//                "Science Fiction",
//                (byte) 4);
//        Book book4 = new Book("Twilight",
//                "Stephenie Meyer",
//                LocalDate.of(2005, 10, 5),
//                18.90f,
//                "Fantasy",
//                (byte) 3);
//        Book book5 = new Book("Dune",
//                "Frank Herbert",
//                LocalDate.of(1965, 6, 1),
//                10.02f,
//                "Science Fantasy",
//                (byte) 4);
//        Book book6 = new Book("Empire of AI: Dreams and Nightmares in Sam Altman's OpenAI",
//                "Karen Hao",
//                LocalDate.of(2025, 5, 20),
//                16.99f,
//                "Science",
//                (byte) 4);

//        book1.setTitle("Clean Code");
//        book1.setAuthor("Robert C. Martin");
//        book1.setPublishDate(LocalDate.of(2008, 8, 1));
//        book1.setGenre("Non-Fiction");
//        book1.setPrice(59.95f);
//        book1.setRating((byte)4);

        console.start();

//        List<Book> allBooks = bookManager.getAllBooks();


//        System.out.println(allBooks);
//        System.out.println(bookManager.getAllBooks());

//        System.out.println(bookManager.updateBook(2, book1));
//        System.out.println(bookManager.getBooksByAuthor("Robert C. Martin"));
//        System.out.println(bookManager.getAllBooks());


//        System.out.println("Please enter the first number!");
//        int num = scanner.nextInt();
//        System.out.println("Please enter the second number!");
//        int num2 = scanner.nextInt();
//        try {
//            int results = num/num2;
//        } catch (ArithmeticException e) {
//            System.out.println("You can't divide by zero");
//        } finally {
//
//        }
        // Can be used in any environment minus threading not thread safe
//        StringBuilder sBuilder = new StringBuilder();
//        // Thread safe
//        StringBuffer sBuffer = new StringBuffer();
        //accessFile();
        //System.out.println(bookSort.compare(book1, book2));
    }

//    private static void accessFile() throws IOException {
//        FileReader fReader = new FileReader("bad_file.txt");
//    }
}
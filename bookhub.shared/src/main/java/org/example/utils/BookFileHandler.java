package org.example.utils;

import org.example.entities.Book;

import java.io.*;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class BookFileHandler {
    private static final String BOOK_FILE = "bookhub_data.csv";

    private static String bookToString(Book book) {
        LocalDateTime shortenDateTime = book.getDateAdded().truncatedTo(ChronoUnit.MINUTES);
        //Format: id,title,author,genre,publishDate,price,rating,dateAdded
        return String.format("%d,%s,%s,%s,%f,%s,%d,%s",
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getPublishDate(),
                book.getPrice(),
                book.getGenre(),
                book.getRating(),
                shortenDateTime
        );
    }

    private static Book stringToBook(String bookString) {
        Book book = new Book();
        String[] bookStrings = bookString.split(",");

//		//Format: id,title,author,genre,publishDate,price,rating,dateAdded
        book.setId(Integer.parseInt(bookStrings[0]));
        book.setTitle(bookStrings[1]);
        book.setAuthor(bookStrings[2]);
        book.setPublishDate(LocalDate.parse(bookStrings[3]));
        book.setPrice(Float.parseFloat(bookStrings[4]));
        book.setGenre(bookStrings[5]);
        book.setRating(Byte.parseByte(bookStrings[6]));
        book.setDateAdded(LocalDateTime.parse(bookStrings[7]));
//		int pointer = 0;
//		for(Field field : book.getClass().getDeclaredFields()) {
//			if(pointer > 7) break;
//			try {
//				field.setAccessible(true);
//				field.set(book, bookStrings[pointer++]);
//				field.setAccessible(false);
//			} catch (IllegalArgumentException | IllegalAccessException e) {
//				System.err.println("Error has occurred during parsing: " + e.getMessage());
//			}
//		}

        return book;
    }

    public static void saveBooks(List<Book> bookList) {
        try (FileWriter fileWriter = new FileWriter(BOOK_FILE);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
             for (Book book: bookList) {
                 bufferedWriter.write(bookToString(book));
                 bufferedWriter.newLine();
             }
             System.out.println("Books Saved Successfully");
        } catch (IOException e) {
            System.err.println("Error saving books: " + e.getMessage());
        }
    }

    public static List<Book> loadBooks() {
        List<Book> books = new ArrayList<Book>();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return books;
        }

        try (FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader)) {
            String lineString;

            while ((lineString = bufferedReader.readLine()) != null) {
                Book book = stringToBook(lineString);
                if (book != null || book.getTitle() != null) {
                    books.add(book);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading books: " + e.getMessage());
        }

        return books;
    }
}

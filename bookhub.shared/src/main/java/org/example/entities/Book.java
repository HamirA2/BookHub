package org.example.entities;

import org.example.exceptions.BookInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
/*
* Create 5 more books in Main and improve compare method.*/
public class Book implements Comparable<Book>, Comparator<Book> {
    // Encapsulation
    // Control the flow of Data coming in and out of our classes
    // private field variables.
    // public getters and setters.
    // number primitive data types:
    // byte(8 bits)(-128 - 127), short(16 bits) (-32,768 - 32,767), int(32 bits), long(64 bits)
    // decimal primitives: double(), float
    private final int id;
    private String title;
    private String author;
    private LocalDate publishDate;
    private float price;
    private String genre;
    private byte rating;
    private final LocalDateTime dateAdded;

    private static int idPointer = 1;

    // Constructor
    // special method that initializes the instance object from the class object
    // it's called in conjunction with the "new" keyword.
    public Book() {
        this.id = idPointer;
        this.dateAdded = LocalDateTime.now();
        idPointer++;
    }

    public Book(
            String title,
            String author,
            LocalDate publishDate,
            float price,
            String genre,
            byte rating) {
        if (title == null || title.trim().isEmpty()) {
            throw new BookInputException("Title needs to be filled out!");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new BookInputException("Author needs to be filled out!");
        }
        if (publishDate == null) {
            throw new BookInputException("Have to have a publish date whether in the future or past");
        }
        if (genre == null || genre.trim().isEmpty()) {
            throw new BookInputException("Genre is required to be filled out");
        }

        this.id = idPointer;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.price = price;
        this.genre = genre;
        this.rating = rating;
        dateAdded = LocalDateTime.now();
        idPointer++;
    }

    // Getters/Setters
    /*
    * Used to access the field type variables in the instance object
    * */

    /**
     * This is a method to retrieve the id of the book
     * @return int
     */
    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        if (id > 0) {
//            this.id = id;
//        }
//
//    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new BookInputException("Title must be filled in");
        }

        this.title = title;

    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new BookInputException("Author must be filled in");
        }

        this.author = author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        if (publishDate == null) {
            throw new BookInputException("Publish Date must be in future or past");
        }

        this.publishDate = publishDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;

    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        if (genre == null || genre.trim().isEmpty()) {
            throw new BookInputException("Genre must be filled out");
        }

        this.genre = genre;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
            this.rating = rating;
    }

    public void displayBook() {
        System.out.println("Book Information:\n" +
                "Id: " + getId() +
                "\nTitle: " + getTitle() +
                "\nAuthor: " + getAuthor() +
                "\nPublish Date: " + getPublishDate() +
                "\nPrice: $" + getPrice() +
                "\nGenre: " + getGenre() +
                "\nCurrent Rating: " + getRating() + " Stars"
                );
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return id == book.id
                && Float.compare(price, book.price) == 0
                && rating == book.rating
                && Objects.equals(title, book.title)
                && Objects.equals(author, book.author)
                && Objects.equals(publishDate, book.publishDate)
                && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, publishDate, price, genre, rating);
    }

    // Comparable(single field comparison)
    @Override
    public int compareTo(Book o) {
        if (this.title.compareTo(o.title) == 1) {
            return 1;
        }
        else if (this.title.compareTo(o.title) == -1) {
            return -1;
        }
        return 0;
    }

    // Comparator(multiple comparison)
    @Override
    public int compare(Book book1, Book book2) {
//        if (book1.title.equalsIgnoreCase(book2.title) &&
//            book1.author.equalsIgnoreCase(book2.author) &&
//            book1.publishDate.equals(book2.publishDate)) {
//            return 1;
//        }
//        else if (!book1.title.equalsIgnoreCase(book2.title) &&
//                 !book1.author.equalsIgnoreCase(book2.author) &&
//                 !book1.publishDate.equals(book2.publishDate)) {
//            return -1;
//        }
//        return 0;

//        return book1.title.compareToIgnoreCase(book2.title)
//                - book1.author.compareToIgnoreCase(book2.author)
//                - book1.publishDate.compareTo(book2.publishDate);
        return book1.publishDate.compareTo(book2.publishDate);

        //return book1.title
    }
}

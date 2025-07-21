package org.example.webserver.dto;

import java.time.LocalDate;

public class PostNewBook {
    private final String title;
    private final String author;
    private final String genre;
    private final LocalDate publishDate;
    private final byte rating;


    public PostNewBook(String title, String author, String genre, LocalDate publishDate, byte rating) {
        super();
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishDate = publishDate;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public byte getRating() {
        return rating;
    }
}

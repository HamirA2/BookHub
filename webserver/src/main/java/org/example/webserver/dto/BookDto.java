package org.example.webserver.dto;

// For customers/users DTO
public class BookDto {
    private final int id;
    private final String title;
    private final String author;
    private final String genre;
    private final byte rating;

    public BookDto(int id, String title, String author, String genre, byte rating) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.rating = rating;
    }

    public int getId() {
        return id;
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

    public byte getRating() {
        return rating;
    }
}

package org.example.webserver.dto;

public class ReviewDto {
    private final int id;
    private final String username;
    private final String comment;
    private final byte rating;
    private final int bookId;

    public ReviewDto(int id, String username, String comment, byte rating, int bookId) {
        super();
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.rating = rating;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }

    public byte getRating() {
        return rating;
    }

    public int getBookId() {
        return bookId;
    }
}

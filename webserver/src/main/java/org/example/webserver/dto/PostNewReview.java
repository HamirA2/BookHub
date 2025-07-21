package org.example.webserver.dto;

public class PostNewReview {
    private final String username;
    private final String comment;
    private final byte rating;

    public PostNewReview(String username, String comment, byte rating) {
        this.username = username;
        this.comment = comment;
        this.rating = rating;
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
}

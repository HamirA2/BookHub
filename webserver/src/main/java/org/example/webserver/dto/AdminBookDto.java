package org.example.webserver.dto;

import java.time.LocalDate;

public class AdminBookDto extends BookDto {
    private final float price;
    private final LocalDate publishDate;

    public AdminBookDto(int id,
                        String title,
                        String author,
                        String genre,
                        byte rating,
                        float price,
                        LocalDate publishDate) {
        super(id, title, author, genre, rating);
        this.price = price;
        this.publishDate = publishDate;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }
}

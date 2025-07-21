package org.example.webserver.dto;

import java.time.LocalDate;

public class PostNewBookAdmin extends PostNewBook {
    private final float price;


    public PostNewBookAdmin(String title,
                            String author,
                            String genre,
                            LocalDate publishDate,
                            byte rating,
                            float price) {
        super(title, author, genre, publishDate, rating);
        this.price = price;
    }

    public float getPrice() {
        return price;
    }
}

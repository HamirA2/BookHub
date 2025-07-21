package org.example.webserver.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book", indexes = @Index(columnList = "author, genre"))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    @Size(min = 2, max = 150, message = "Title has to be between 2 and 150 characters long")
    private String title;
    @Column(nullable = false)
    @Size(min = 3, max = 300)
    private String author;
    @Column(nullable = false)
    private LocalDate publishDate;
    @Column(nullable = false)
    @Max(value = 500)
    private float price = 0.00f;
    @Column(nullable = false)
    @Size(min = 6, max = 30)
    private String genre;
    @Column(nullable = false)
    @Min(value = 1)
    @Max(value = 5)
    private byte rating = 1;
    @CreationTimestamp
    private LocalDateTime dateAdded;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Review> reviews;

    public Book(
            int id,
            String title,
            String author,
            LocalDate publishDate,
            String genre,
            byte rating) {
        super();
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.genre = genre;
        this.rating = rating;
        this.price = 0.00f;
    }

    public Book(
            int id,
            String title,
            String author,
            LocalDate publishDate,
            String genre,
            byte rating,
            float price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishDate = publishDate;
        this.genre = genre;
        this.rating = rating;
        this.price = price;
    }
}

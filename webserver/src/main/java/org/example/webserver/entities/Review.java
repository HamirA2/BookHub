package org.example.webserver.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    @Column(nullable = false)
    @Size(max = 50)
    private String username = "Anonymous User";
    @Size(max = 500)
    private String comment;
    @Min(value = 1)
    @Max(value = 5)
    private byte rating;
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @JsonBackReference
    @JsonIgnore
    @ToString.Exclude
    private Book book;
}

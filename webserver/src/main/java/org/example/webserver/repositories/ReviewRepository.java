package org.example.webserver.repositories;

import org.example.webserver.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByUsername(String username);
    List<Review> findAllByBookId(int id);
    Optional<Review> findByReviewId(int id);
    Optional<Review> findByComment(String comment);
}

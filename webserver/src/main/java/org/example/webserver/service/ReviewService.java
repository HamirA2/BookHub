package org.example.webserver.service;

import org.example.webserver.dto.PostNewReview;
import org.example.webserver.dto.ReviewDto;
import org.example.webserver.entities.Review;

import java.util.List;

public interface ReviewService {
    List<ReviewDto> getAllReviews();
    List<ReviewDto> getAllReviewsByBookId(int bookId);
    List<ReviewDto> getAllReviewsByUsername(String username);
    ReviewDto getReviewById(int reviewId);
    ReviewDto createReview(PostNewReview review);
    ReviewDto updateReview(ReviewDto reviewDto);
    void deleteReviewById(int reviewId);
}

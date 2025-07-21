package org.example.webserver.service;

import org.example.webserver.dto.PostNewReview;
import org.example.webserver.dto.ReviewDto;
import org.example.webserver.entities.Review;
import org.example.webserver.exceptions.ReviewNotFoundException;
import org.example.webserver.repositories.ReviewRepository;

import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(review -> {
                    return new ReviewDto(review.getReviewId(),
                            review.getUsername(),
                            review.getComment(),
                            review.getRating(),
                            review.getBook().getId());
                })
                .toList();
    }

    @Override
    public List<ReviewDto> getAllReviewsByBookId(int bookId) {
        return reviewRepository.findAllByBookId(bookId).stream()
                .map(review -> {
                    return new ReviewDto(review.getReviewId(),
                            review.getUsername(),
                            review.getComment(),
                            review.getRating(),
                            review.getBook().getId());
                })
                .toList();
    }

    @Override
    public List<ReviewDto> getAllReviewsByUsername(String username) {
        return reviewRepository.findAllByUsername(username).stream()
                .map(review -> {
                    return new ReviewDto(review.getReviewId(),
                            review.getUsername(),
                            review.getComment(),
                            review.getRating(),
                            review.getBook().getId());
                })
                .toList();
    }

    @Override
    public ReviewDto getReviewById(int id) {
        Review review = reviewRepository.findByReviewId(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review with" + id + " not found!"));
        return new ReviewDto(review.getReviewId(),
                review.getUsername(),
                review.getComment(),
                review.getRating(),
                review.getBook().getId());
    }

    @Override
    public ReviewDto createReview(PostNewReview review) {
        return null;
    }

    @Override
    public ReviewDto updateReview(ReviewDto reviewDto) {
        return null;
    }

    @Override
    public void deleteReviewById(int reviewId) {
        if (reviewRepository.existsById(reviewId)) {
            reviewRepository.deleteById(reviewId);
        } else {
            throw new ReviewNotFoundException("Review with " + reviewId + " not found!");
        }
    }
}

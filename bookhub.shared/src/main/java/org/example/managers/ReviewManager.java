package org.example.managers;

import org.example.entities.Book;
import org.example.entities.Review;
import org.example.exceptions.ReviewInputException;
import org.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ReviewManager {

    @Autowired
    private ReviewRepository reviewRepository;

    private static boolean updateTracker = false;

    public ReviewManager(ReviewRepository reviewRepository) {

    }

//    public static List<Review> getAllBookReviews(List<Book> books) {
//        List<Book> bookWithReviews = books.stream()
//                .filter(book -> !book.getReviews().isEmpty()).toList();
//
//        List<Review> reviews = new ArrayList<Review>();
//        reviews.add(bookWithReviews.stream().forEach(book -> book.getReviews()));
//
//    }

    public static List<Review> getAllBookReviewsFromBook(Book book) {
        return book.getReviews();
    }

    public static Review getBookReviewById(Book book, int reviewId) {
        for (Review review : book.getReviews()) {
            if (review.getReviewId() == reviewId) {
                return review;
            }
        }

        return null;
    }

    public static List<Review> getBookReviewsByUsername(Book book, String username) {
        List<Review> reviews = new ArrayList<Review>();

        for (Review review : book.getReviews()) {
            if (review.getUsername().equals(username)) {
                reviews.add(review);
            }
        }

        return reviews;
    }

    public static void addReview(Book book,
                            String username,
                            String comment,
                            byte rating) {
        if (comment.isBlank()) throw new ReviewInputException("Comment cannot be blank for a review");
        if (rating < 0 || rating > 5) throw new ReviewInputException("Rating cannot exceed 5 or go below 0");
        Review review = new Review(username, comment, rating);
        book.getReviews().add(review);
        System.out.println("Review successfully added!");
        updateTracker = true;
    }

    public static boolean updateReview(Book book, int reviewId, String comment, byte rating) {
        boolean results = false;
        Review review = getBookReviewById(book, reviewId);

        if (review.getComment().equals(comment) || comment.isBlank()) {
            exceptionThrower("Comment is unchanged or is blank!");
        }
        else if (review.getRating() == rating || (rating < 0 || rating > 5)) {
            exceptionThrower("Rating is unchanged or not between 1-5!");
        }

        review.setComment(comment);
        review.setRating(rating);
        results = true;
        updateTracker = true;
        return results;
    }

    public static boolean deleteReviewById(Book book, int reviewId) {
        return book.getReviews().removeIf(b -> b.getReviewId() == reviewId);
    }

    private static void exceptionThrower(String message) {
        throw new ReviewInputException(message);
    }

//    public Runnable autoSaveRunnable() {
//        Runnable runnable = () -> {
//            while (true) {
//                if (updateTracker) {
//                    get
//                }
//            }
//        };
//    }
}

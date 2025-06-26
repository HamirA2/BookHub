package org.example.managers;

import org.example.entities.Book;
import org.example.entities.Review;
import org.example.exceptions.ReviewInputException;

import java.util.List;

public class ReviewManager {
    public static List<Review> getAllBookReviews(Book book) {
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

    public static void addReview(Book book,
                            String username,
                            String comment,
                            byte rating) {
        if (comment.isBlank()) throw new ReviewInputException("Comment cannot be blank for a review");
        if (rating < 0 || rating > 5) throw new ReviewInputException("Rating cannot exceed 5 or go below 0");
        Review review = new Review(username, comment, rating);
        book.getReviews().add(review);
        System.out.println("Review successfully added!");
    }

    // TODO: OPTIMIZE THIS (maybe top add too) USE METHOD TO THROW EXCEPTIONS
    public static boolean updateReview(Book book, int reviewId, String comment, byte rating) {
        boolean results = false;
        Review review = getBookReviewById(book, reviewId);

//        if (!review.getComment().equals(comment) || !comment.isBlank()) {
//            review.setComment(comment);
//            results = true;
//        }
//        if (review.getRating() != rating && !(rating < 0 || rating > 5)) {
//            review.setRating(rating);
//            results = true;
//        }

        if (review.getComment().equals(comment) || comment.isBlank()) {
            exceptionThrower("Comment is unchanged or is blank!");
        }
        else if (review.getRating() == rating || (rating < 0 || rating > 5)) {
            exceptionThrower("Rating is unchanged or not between 1-5!");
        }

        review.setComment(comment);
        review.setRating(rating);
        results = true;

        return results;
    }

    public static boolean deleteReviewById(Book book, int reviewId) {
        return book.getReviews().removeIf(b -> b.getReviewId() == reviewId);
    }

    private static void exceptionThrower(String message) {
        throw new ReviewInputException(message);
    }
}

package org.example.repository;

import org.example.entities.Book;
import org.example.entities.Review;
import org.example.managers.ReviewManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {
    private Connection connection;

    public ReviewRepository() {
        connection = DatabaseConnection.getInstance().getConnection();
    }

    public Review save(Review review) {
        String sqlString = "INSERT INTO reviews (username, comment, rating) " +
                "VALUE (?,?,?)";
        String[] returnedColumnStrings = {"id", "date_added", "book_id"};

        try (PreparedStatement statement = connection.prepareStatement(sqlString, returnedColumnStrings)) {
            statement.setString(1, review.getUsername());
            statement.setString(2, review.getComment());
            statement.setByte(3, review.getRating());

            int rows = statement.executeUpdate();
            if (rows == 0) throw new SQLException("Creating a review failed, no rows affected");

            try (var generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int reviewId = generatedKeys.getInt(1);
                    String fetchTimeStampSql = "SELECT date_added FROM reviews WHERE id = ?";

                    try (PreparedStatement fetchStmt = connection.prepareStatement(fetchTimeStampSql)) {
                        fetchStmt.setInt(1, reviewId);

                        try (var rs = fetchStmt.executeQuery()) {
                            if (rs.next()) {
                                review.setDateCreated(rs.getTimestamp("date_added").toLocalDateTime());
                            }
                        }
                    }
                }
            }

            return review;
        } catch (SQLException e) {
            System.err.println("Error has occurred while saving: " + e.getMessage());
        }

        return null;
    }

    public List<Review> findAllReviews() throws SQLException {
        List<Review> reviews = new ArrayList<Review>();
        String sqlString = "SELECT * FROM reviews";

        try (Statement statement = connection.prepareStatement(sqlString)) {
            ResultSet reviewSet = statement.executeQuery(sqlString);

            while (reviewSet.next()) {
                Review review = new Review();
                review.setReviewId(reviewSet.getInt(1));
                review.setUsername(reviewSet.getString(2));
                review.setComment(reviewSet.getString(3));
                review.setRating(reviewSet.getByte(4));
                review.setDateCreated(reviewSet.getTimestamp(5).toLocalDateTime());
                reviews.add(review);
                review = null;
            }

            return reviews;
        }
    }
}

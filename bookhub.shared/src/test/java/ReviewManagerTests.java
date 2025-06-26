import org.example.managers.BookManager;
import org.example.managers.ReviewManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewManagerTests {
    private BookManager bookManager;

    @BeforeEach
    private void setUp() {
        bookManager = new BookManager();

        bookManager.addBook("The Martian",
                "Andy Weir",
                LocalDate.of(2014, 10, 28),
                9.00f,
                "Science Fiction",
                (byte) 4);
    }

    @AfterEach
    private void cleanUp() {
        bookManager = null;
    }

    @Test
    @DisplayName("Should add review to a book")
    void addReviewToABook() {
//        reviewManager.addReview(bookManager.getBookByTitle("The Martian"),
//                "",
//                "I like this book!",
//                (byte) 4);
        ReviewManager.addReview(bookManager.getBookByTitle("The Martian"),
                "",
                "I like this book!",
                (byte) 4);

        ReviewManager.addReview(bookManager.getBookByTitle("The Martian"),
                "Second",
                "Ok book",
                (byte) 4);

        assertEquals("Anonymous User",
                ReviewManager.getBookReviewById(bookManager.getBookByTitle("The Martian"), 1)
                .getUsername());
        assertEquals("Second",
                ReviewManager.getBookReviewById(bookManager.getBookByTitle("The Martian"), 2)
                        .getUsername());
        assertEquals("Ok book",
                ReviewManager.getBookReviewById(bookManager.getBookByTitle("The Martian"), 2)
                        .getComment());
    }

    @Test
    @DisplayName("Should return a review using the review id")
    void getReviewUsingIdReturnsAReview() {

        assertEquals(1,
                ReviewManager.getBookReviewById(bookManager.getBookByTitle("The Martian"), 1)
                        .getReviewId());
    }
}

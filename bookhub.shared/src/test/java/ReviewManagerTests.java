//import org.example.entities.Book;
//import org.example.entities.Review;
//import org.example.managers.BookManager;
//import org.example.managers.ReviewManager;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ReviewManagerTests {
//    private BookManager bookManager;
//
//    @BeforeEach
//    private void setUp() {
//        bookManager = new BookManager();
//
//        bookManager.addBook("The Martian",
//                "Andy Weir",
//                LocalDate.of(2014, 10, 28),
//                9.00f,
//                "Science Fiction",
//                (byte) 4);
//    }
//
//    @AfterEach
//    private void cleanUp() {
//        bookManager = null;
//    }
//
//    @Test
//    @DisplayName("Should add review to a book")
//    void addReviewToABook() {
//        Book book = bookManager.getBookByTitle("The Martian");
//        ReviewManager.addReview(book,
//                "",
//                "I like this book!",
//                (byte) 4);
//
//        ReviewManager.addReview(book,
//                "Second",
//                "Ok book",
//                (byte) 4);
//
//        Review review1 = book.getReviews().getFirst();
//        Review review2 = book.getReviews().get(1);
//
//        assertEquals("Anonymous User",
//                review1.getUsername());
//        assertEquals("Second",
//               review2.getUsername());
//        assertEquals("Ok book",
//                review2.getComment());
//    }
//
//    @Test
//    @DisplayName("Should return a review using the review id")
//    void getReviewUsingIdReturnsAReview() {
//        Book book = bookManager.getBookByTitle("The Martian");
//
//        ReviewManager.addReview(bookManager.getBookByTitle("The Martian"),
//                "myself",
//                "I like this book!",
//                (byte) 4);
//
//        Review review = book.getReviews().getFirst();
//
//        assertEquals(review,
//                ReviewManager.getBookReviewById(book, review.getReviewId()));
//
//    }
//
//    @Test
//    @DisplayName("Should update a review using the review id")
//    void updateReviewUsingReviewIdShouldUpdateReview() {
//        Book book = bookManager.getBookByTitle("The Martian");
//
//        ReviewManager.addReview(book,
//                "",
//                "I love this book!",
//                (byte) 4);
//
//        Review review = book.getReviews().getFirst();
//
//        ReviewManager.updateReview(book,
//                review.getReviewId(),
//                "Changed Comment",
//                (byte) 2);
//
//        assertEquals("Changed Comment",
//                ReviewManager.getBookReviewById(book, review.getReviewId())
//                        .getComment());
//    }
//
//    @Test
//    @DisplayName("Should delete a review using the review id!")
//    void deleteReviewUsingReviewIdShouldDeleteTheReview() {
//        Book book = bookManager.getBookByTitle("The Martian");
//
//        ReviewManager.addReview(book,
//                "1st",
//                "Delete me!",
//                (byte) 1);
//
//        Review review = book.getReviews().getFirst();
//
//        assertTrue(ReviewManager.deleteReviewById(book, review.getReviewId()));
//    }
//}

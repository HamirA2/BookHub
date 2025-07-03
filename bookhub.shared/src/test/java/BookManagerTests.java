//import org.example.entities.Book;
//import org.example.managers.BookManager;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//
//import java.lang.reflect.Field;
//import java.time.LocalDate;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class BookManagerTests {
//    private BookManager bookManager;
//
//    @BeforeEach
//    private void setUp() {
//        bookManager = new BookManager();
//
//        bookManager.addBook("Adventures of Huckleberry Finn",
//                "Mark Twain",
//                LocalDate.of(1884, 12, 10),
//                15.00f,
//                "Fiction",
//                (byte) 5);
//        bookManager.addBook("The Martian",
//                "Andy Weir",
//                LocalDate.of(2014, 10, 28),
//                9.00f,
//                "Science Fiction",
//                (byte) 4);
//        bookManager.addBook("Twilight",
//                "Stephenie Meyer",
//                LocalDate.of(2005, 10, 5),
//                18.90f,
//                "Fantasy",
//                (byte) 3);
//    }
//
//    @AfterEach
//    private void cleanUp() {
//        bookManager = null;
//    }
//
//    @Test
//    @DisplayName("Should give accurate size of array when tested!")
//    void testGetAllBooksSuccessTest() {
//        assertEquals(3, bookManager.getAllBooks().size());
//    }
//
//    @Test
//    @DisplayName("Should update the book in the Array!")
//    void testUpdateBookShouldUpdateTheBook() {
//        Book oldBook = bookManager.getBookById(3);
//
//        Book expectedBook = new Book(oldBook.getId(), oldBook.getDateAdded());
//        expectedBook.setTitle("Dirty Code");
//        expectedBook.setAuthor("Mohammed");
//        expectedBook.setGenre("Non-Fiction");
//        expectedBook.setPublishDate(LocalDate.of(2025, 6, 23));
//        expectedBook.setRating((byte)0);
//
//        assertTrue(bookManager.updateBook(expectedBook));
//        Book updateBook = bookManager.getBookById(3);
//        assertEquals(expectedBook.getTitle(), updateBook.getTitle());
//    }
//
//    // SUCCESS TEST ADD BOOK/DELETE
//    @Test
//    @DisplayName("Should add the book in the array")
//    void testAddBookShouldAddABook() {
//
//        bookManager.addBook("My Book",
//                "Me",
//                LocalDate.of(2014, 10, 28),
//                9.00f,
//                "Fiction",
//                (byte) 1);
//
//        assertEquals("My Book", bookManager.getBookByTitle("My Book").getTitle());
//        assertEquals("Me", bookManager.getBookByTitle("My Book").getAuthor());
//        assertEquals(LocalDate.of(2014, 10,28),
//                bookManager.getBookByTitle("The Martian").getPublishDate());
//        assertEquals(9.00f, bookManager.getBookByTitle("My Book").getPrice());
//        assertEquals("Fiction", bookManager.getBookByTitle("My Book").getGenre());
//        assertEquals((byte) 1, bookManager.getBookByTitle("My Book").getRating());
//    }
//
//    @Test
//    @DisplayName("Should delete the book in the array")
//    void testDeleteBookByIdShouldDeleteBook() {
//        bookManager.deleteBookById(1);
//        assertTrue(bookManager.getBookByTitle("Adventures of Huckleberry Finn").getId()
//            != 1);
//    }
//
//    // VALIDATION TEST (ASSERT THROWS) PARAM TEST AND VALUE SOURCE
////    @ParameterizedTest
////    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
////    void getBookShouldReturnSameValue(int bookId) {
////        assertEquals(bookId, bookManager.getBookById(bookId).getId());
////    }
//
//}

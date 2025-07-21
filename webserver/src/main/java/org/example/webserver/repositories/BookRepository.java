package org.example.webserver.repositories;

import org.example.webserver.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// JPA Repositories
// CrudRepository
// ListCrudRepository
// PagingAndSortingRepository
// JpaRepository
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    long countByTitle(String title);
    List<Book> findAllByAuthor(String author);
    @Query("SELECT b.genre as genre, COUNT(b.id) as count FROM Book b GROUP BY b.genre")
    List<GenreStatsProjection> getStatsOfLibrary();
    Optional<Book> findByTitle(String title);
    List<Book> findAllByGenreIgnoreCase(String genre);
}

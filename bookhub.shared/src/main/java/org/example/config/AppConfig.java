package org.example.config;

import org.example.managers.BookManager;
import org.example.managers.ReviewManager;
import org.example.repository.BookRepository;
import org.example.repository.ReviewRepository;
import org.example.ui.BookHubConsole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Scanner;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("org.example")
public class AppConfig {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @Bean
    public BookRepository bookRepository() {
        return new BookRepository();
    }

    @Bean
    public BookManager bookManager(BookRepository bookRepository) {
        return new BookManager(bookRepository);
    }

    @Bean
    public BookHubConsole bookHubConsole(BookManager bookManager, Scanner scanner) {
        return new BookHubConsole(bookManager, scanner);
    }

    @Bean
    public ReviewRepository reviewRepository() {
        return new ReviewRepository();
    }

    @Bean
    public ReviewManager reviewManager(ReviewRepository reviewRepository) {
        return new ReviewManager(reviewRepository);
    }
}

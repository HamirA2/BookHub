package org.example.webserver;

import lombok.extern.slf4j.Slf4j;
import org.example.webserver.dto.PostNewBook;
import org.example.webserver.dto.PostNewBookAdmin;
import org.example.webserver.entities.Book;
import org.example.webserver.repositories.BookRepository;
import org.example.webserver.repositories.ReviewRepository;
import org.example.webserver.utils.BookServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;


@SpringBootApplication
@Slf4j
public class WebserverApplication implements CommandLineRunner {
	// setup for a logger boilerplate
//	private static final Logger logger = LoggerFactory.getLogger(WebserverApplication.class);

	@Autowired
	private BookServiceFactory bookServiceFactory;

	@Autowired
	private ReviewRepository reviewRepository;

	public static void main(String[] args) {
		SpringApplication.run(WebserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Test with all methods in user and admin

	}
}

package org.example;

import org.example.config.AppConfig;
import org.example.entities.Book;
import org.example.managers.BookManager;
import org.example.repository.BookRepository;
import org.example.ui.BookHubConsole;
import org.example.utils.BookFileHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
//        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
//        Scanner scanner = new Scanner(System.in);
//        BookRepository bookRepository = new BookRepository();
//        BookManager bookManager = new BookManager(bookRepository);
//        BookHubConsole console = new BookHubConsole(bookManager, scanner);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        BookHubConsole console = context.getBean(BookHubConsole.class);
        console.start();

    }
}
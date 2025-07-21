package org.example.webserver.utils;

import org.example.webserver.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.HttpRequestHandlerServlet;


@Component
public class BookServiceFactory {

    @Autowired
    private BookService userBookService;

    @Autowired
    @Qualifier("admin")
    private BookService adminBookService;

    public BookService getUserBookService(String roleString) {
        if (roleString != null && roleString.equals("admin")) {
            return adminBookService;
        }
        return userBookService;
    }
}

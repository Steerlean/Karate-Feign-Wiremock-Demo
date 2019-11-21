package com.example.feign.controller;

import com.example.feign.feign.Book;
import com.example.feign.feign.IBookClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final IBookClient bookClient;

    @Autowired
    public BookController(IBookClient bookClient) {
        this.bookClient = bookClient;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookTitle")
    public ResponseEntity<String> bookTitle() {
        return ResponseEntity.ok(bookClient.bookTest());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/bookSearch")
    public ResponseEntity<Book> bookSearch(String id) {
        return ResponseEntity.ok(bookClient.findById(id));
    }

}

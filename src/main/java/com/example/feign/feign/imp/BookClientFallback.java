package com.example.feign.feign.imp;

import com.example.feign.feign.Book;
import com.example.feign.feign.BookClient;
import org.springframework.stereotype.Component;

@Component
public class BookClientFallback implements BookClient {
    @Override
    public Book findById(String id) {
        return Book.builder().id("fallback-id").title("default").isbn("default").build();
    }

    @Override
    public String bookTest() {
        return "testing 123";
    }
}
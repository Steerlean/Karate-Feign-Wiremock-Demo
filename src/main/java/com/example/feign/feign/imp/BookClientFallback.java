package com.example.feign.feign.imp;

import com.example.feign.feign.Book;
import com.example.feign.feign.IBookClient;
import org.springframework.stereotype.Component;

@Component
public class BookClientFallback implements IBookClient {
    @Override
    public Book findById(String id) {
        return Book.builder().id("fallback-id").title("default").isbn("default").build();
    }

    @Override
    public String bookTest() {
        return "Fallback123";
    }
}

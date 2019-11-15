package com.example.feign.feign;

public class BookBuilder extends Book {

    private Book book;

    public BookBuilder() {
        this.book = book;
    }

    public BookBuilder id(String id) {
        book.setId(id);
        return this;
    }

    public BookBuilder title(String title) {
        book.setTitle(title);
        return this;
    }

    public BookBuilder isbn(String isbn) {
        book.setIsbn(isbn);
        return this;
    }

    public Book build() {
        return book;
    }
}

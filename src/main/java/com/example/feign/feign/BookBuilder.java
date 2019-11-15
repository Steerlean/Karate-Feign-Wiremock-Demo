package com.example.feign.feign;

public class BookBuilder extends Book {

    private Book book;

    public BookBuilder(Book book) {
        this.book = book;
    }

    public BookBuilder id(String id) {
        this.book.setId(id);
        return this;
    }

    public BookBuilder title(String title) {
        this.book.setTitle(title);
        return this;
    }

    public BookBuilder isbn(String isbn) {
        this.book.setIsbn(isbn);
        return this;
    }

    public Book build() {
        return this.book;
    }
}

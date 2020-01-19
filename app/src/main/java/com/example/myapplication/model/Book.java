package com.example.myapplication.model;

import androidx.annotation.NonNull;

public class Book {
    private String id;
    private BookType bookType;
    private String name;
    private String author;
    private String publishingCompany;
    private float priceBook;

    public Book(String id, BookType bookType, String name, String author, String publishingCompany, float priceBook) {
        this.id = id;
        this.bookType = bookType;
        this.name = name;
        this.author = author;
        this.publishingCompany = publishingCompany;
        this.priceBook = priceBook;
    }

    public Book(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public float getPriceBook() {
        return priceBook;
    }

    public void setPriceBook(float priceBook) {
        this.priceBook = priceBook;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "-" + name;
    }
}

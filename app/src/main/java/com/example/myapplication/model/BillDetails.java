package com.example.myapplication.model;

public class BillDetails {
    private String id;
    private Bill bill;
    private Book book;
    private int amount;

    public BillDetails(String id, Bill bill, Book book, int amount) {
        this.id = id;
        this.bill = bill;
        this.book = book;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}

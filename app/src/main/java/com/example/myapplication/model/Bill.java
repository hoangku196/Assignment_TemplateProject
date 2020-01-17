package com.example.myapplication.model;

public class Bill {
    private String id;
    private String date;

    public Bill(String id, String date) {
        this.id = id;
        this.date = date;
    }

    public Bill(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

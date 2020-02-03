package com.example.myapplication.model;

import androidx.annotation.NonNull;

public class BookType {
    private String id;
    private String name;
    private String describe;
    private int location;

    public BookType(String id, String name, String describe, int location) {
        this.id = id;
        this.name = name;
        this.describe = describe;
        this.location = location;
    }

    public BookType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    @NonNull
    @Override
    public String toString() {
        return id + "-" + name;
    }
}

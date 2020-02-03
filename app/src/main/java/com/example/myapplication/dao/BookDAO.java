package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Book;
import com.example.myapplication.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String SQL_BOOK = "CREATE TABLE BOOK(ID TEXT PRIMARY KEY, " +
            "IDBOOKTYPE TEXT, " +
            "NAME TEXT, " +
            "AUTHOR TEXT, " +
            "PUBLISHINGCOMPANY TEXT, " +
            "PRICEBOOK FLOAT, " +
            "AMOUNT INTEGER)";
    public static final String TABLE_NAME = "BOOK";
    private final String TAG = this.getClass().getSimpleName();

    public BookDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("ID", book.getId());
        values.put("IDBOOKTYPE", book.getBookType().getId());
        values.put("NAME", book.getName());
        values.put("AUTHOR", book.getAuthor());
        values.put("PUBLISHINGCOMPANY", book.getPublishingCompany());
        values.put("PRICEBOOK", book.getPublishingCompany());
        values.put("AMOUNT", book.getAmount());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public int getAmountBookById(String idBook) {

        Cursor cursor = db.rawQuery("SELECT AMOUNT FROM " + TABLE_NAME + " WHERE ID=?", new String[]{idBook});
        int amount = 0;

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            amount = cursor.getInt(0);
            cursor.moveToNext();
        }

        cursor.close();

        return amount;
    }

    public List<Book> getAllBook() {
        List<Book> books = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String idBookType = cursor.getString(1);
            String nameBookType = null;
            String describeBookType = null;
            int locationBookType = 0;

            //TODO
            Cursor cursorGetBookType = db.rawQuery("SELECT NAME, DESCRIBE, LOCATION FROM " + BookTypeDAO.TABLE_NAME + " WHERE ID=?", new String[]{idBookType});
            cursorGetBookType.moveToFirst();
            while (!cursorGetBookType.isAfterLast()) {
                nameBookType = cursorGetBookType.getString(0);
                describeBookType = cursorGetBookType.getString(1);
                locationBookType = cursorGetBookType.getInt(2);

                cursorGetBookType.moveToNext();
            }

            String name = cursor.getString(2);
            String author = cursor.getString(3);
            String publishingCompany = cursor.getString(4);
            float priceBook = cursor.getFloat(5);
            int amount = cursor.getInt(6);
            Book book = new Book(id, new BookType(idBookType, nameBookType, describeBookType, locationBookType), name, author, publishingCompany, priceBook, amount);
            books.add(book);

            cursorGetBookType.close();
            cursor.moveToNext();
        }

        cursor.close();

        return books;
    }

    public boolean updateBook(Book book) {
        ContentValues values = new ContentValues();
        values.put("ID", book.getId());
        values.put("ID", book.getId());
        values.put("IDBOOKTYPE", book.getBookType().getId());
        values.put("NAME", book.getName());
        values.put("AUTHOR", book.getAuthor());
        values.put("PUBLISHINGCOMPANY", book.getPublishingCompany());
        values.put("PRICEBOOK", book.getPublishingCompany());
        values.put("AMOUNT", book.getAmount());

        try {
            if (db.update(TABLE_NAME, values, "ID=?", new String[]{book.getId()}) <= 0)
                return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public boolean deleteBook(Book book) {
        return db.delete(TABLE_NAME, "USERNAME=?", new String[]{book.getId()}) > 0;
    }
}

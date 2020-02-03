package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.BookType;

import java.util.ArrayList;
import java.util.List;

public class BookTypeDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String SQL_BOOKTYPE = "CREATE TABLE BOOKTYPE(ID TEXT PRIMARY KEY NOT NULL, " +
            "NAME TEXT NOT NULL, " +
            "DESCRIBE TEXT, " +
            "LOCATION INTEGER)";
    public static final String TABLE_NAME = "BOOKTYPE";
    private final String TAG = this.getClass().getSimpleName();

    public BookTypeDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertBookType(BookType bookType) {
        ContentValues values = new ContentValues();
        values.put("ID", bookType.getId());
        values.put("NAME", bookType.getName());
        values.put("DESCRIBE", bookType.getDescribe());
        values.put("LOCATION", bookType.getLocation());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public List<BookType> getAllBookType() {
        List<BookType> bookTypes = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String describe = cursor.getString(2);
            int location = cursor.getInt(3);
            BookType bookType = new BookType(id, name, describe, location);
            bookTypes.add(bookType);
            cursor.moveToNext();
        }

        cursor.close();

        return bookTypes;
    }

    public boolean updateBookType(BookType bookType) {
        ContentValues values = new ContentValues();
        values.put("ID", bookType.getId());
        values.put("NAME", bookType.getName());
        values.put("DESCRIBE", bookType.getDescribe());
        values.put("LOCATION", bookType.getLocation());

        try {
            if (db.update(TABLE_NAME, values, "ID=?", new String[]{bookType.getId()}) <= 0)
                return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public boolean deleteBookType(BookType bookType) {
        return db.delete(TABLE_NAME, "ID=?", new String[]{bookType.getId()}) > 0;
    }
}

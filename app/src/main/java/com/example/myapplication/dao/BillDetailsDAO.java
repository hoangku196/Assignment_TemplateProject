package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.fragment.BillDetail;
import com.example.myapplication.model.Bill;
import com.example.myapplication.model.BillDetails;
import com.example.myapplication.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BillDetailsDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String SQL_BILL_DETAILS = "CREATE TABLE BILLDETAILS(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "IDBILL TEXT NOT NULL, " +
            "IDBOOK TEXT NOT NULL, " +
            "AMOUNT INTEGER) ";
    public static final String TABLE_NAME = "BILLDETAILS";
    private final String TAG = this.getClass().getSimpleName();

    public BillDetailsDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertBillDetails(BillDetails bd) {
        ContentValues values = new ContentValues();
        values.put("ID", bd.getId());
        values.put("IDBILL", bd.getBill().getId());
        values.put("IDBOOK", bd.getBook().getId());
        values.put("AMOUNT", bd.getAmount());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public List<Book> getTopSellingBook(int top) {
        List<Book> books = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT TOP " + top + " FROM ( SELECT SUM(AMOUNT) FROM " + TABLE_NAME + " )", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String idBook = cursor.getString(2);
            int amount = cursor.getInt(3);
            books.add(new Book(idBook, amount));

            cursor.moveToNext();
        }
        cursor.close();

        return books;
    }

    public List<BillDetails> getAllBillDetails(String idBill) {
        List<BillDetails> billDetails = new ArrayList<>();

        String selection = "IDBILL=?";
        String[] selectionArgs = new String[]{idBill};

        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String getId = cursor.getString(0);
            String getIdBill = cursor.getString(1);
            String getIdBook = cursor.getString(2);
            int getAmount = cursor.getInt(3);

            BillDetails detail = new BillDetails(getId, new Bill(getIdBill), new Book(getIdBook), getAmount);
            billDetails.add(detail);
            cursor.moveToNext();
        }

        cursor.close();

        return billDetails;
    }

    public boolean updateBillDetails(BillDetails billDetails) {
        ContentValues values = new ContentValues();
        values.put("ID", billDetails.getId());
        values.put("IDBILL", billDetails.getBill().getId());
        values.put("IDBOOK", billDetails.getBook().getId());
        values.put("AMOUNT", billDetails.getAmount());

        try {
            if (db.update(TABLE_NAME, values, "ID=?", new String[]{billDetails.getId()}) <= 0)
                return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public boolean deleteBillDetails(BillDetails billDetails) {
        return db.delete(TABLE_NAME, "USERNAME=?", new String[]{billDetails.getId()}) > 0;
    }
}

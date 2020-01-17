package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Bill;
import com.example.myapplication.model.BillDetails;
import com.example.myapplication.model.Book;

import java.util.ArrayList;
import java.util.List;

public class BillDetailsDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String SQL_BILL_DETAILS = "CREATE TABLE BILLDETAILS(ID TEXT PRIMARY KEY, " +
            "IDBILL TEXT, " +
            "IDBOOK TEXT, " +
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

    public List<BillDetails> getAllBillDetails() {
        List<BillDetails> billDetails = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String idBill = cursor.getString(1);
            String idBook = cursor.getString(2);
            int amount = cursor.getInt(3);

            BillDetails detail = new BillDetails(id, new Bill(idBill), new Book(idBook), amount);
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

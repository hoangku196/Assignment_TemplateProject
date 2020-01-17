package com.example.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myapplication.database.DatabaseHelper;
import com.example.myapplication.model.Bill;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String SQL_BILL = "CREATE TABLE Bill(ID TEXT PRIMARY KEY NOT NULL, " +
            "DATE TEXT NOT NULL)";
    public static final String TABLE_NAME = "Bill";
    private final String TAG = this.getClass().getSimpleName();
    public static int NUMBER_RECORD;

    public BillDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public boolean insertBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put("ID", bill.getId());
        values.put("DATE", bill.getDate());

        try {
            if (db.insert(TABLE_NAME, null, values) <= 0) return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public List<Bill> getAllBill() {
        List<Bill> bills = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String date = cursor.getString(1);
            Bill user = new Bill(id, date);
            bills.add(user);
            cursor.moveToNext();
        }

        NUMBER_RECORD = bills.size();

        cursor.close();

        return bills;
    }

    public boolean updateBill(Bill bill) {
        ContentValues values = new ContentValues();
        values.put("ID", bill.getId());
        values.put("DATE", bill.getDate());

        try {
            if (db.update(TABLE_NAME, values, "ID=?", new String[]{bill.getId()}) <= 0)
                return false;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }

        return true;
    }

    public boolean deleteBill(Bill bill) {
        return db.delete(TABLE_NAME, "ID=?", new String[]{bill.getId()}) > 0;
    }
}

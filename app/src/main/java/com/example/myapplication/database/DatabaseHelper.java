package com.example.myapplication.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.dao.BillDAO;
import com.example.myapplication.dao.BillDetailsDAO;
import com.example.myapplication.dao.BookDAO;
import com.example.myapplication.dao.BookTypeDAO;
import com.example.myapplication.dao.UserDAO;
import com.example.myapplication.model.BillDetails;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbManager";
    public static final int VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    //Tạo bảng
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_USER);
        db.execSQL(BillDAO.SQL_BILL);
        db.execSQL(BookTypeDAO.SQL_BOOKTYPE);
        db.execSQL(BookDAO.SQL_BOOK);
        db.execSQL(BillDetailsDAO.SQL_BILL_DETAILS);
    }

    //Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UserDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BillDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BookTypeDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BookDAO.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + BillDetailsDAO.TABLE_NAME);
    }
}

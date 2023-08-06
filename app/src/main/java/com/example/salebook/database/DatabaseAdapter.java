package com.example.salebook.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.salebook.MainActivity;
import com.example.salebook.fragment.FragHome;
import com.example.salebook.fragment.FragSignIn;
import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SaleBookDB.db";
    private static final String TABLE_USER = "User";
    private static final String TABLE_ROLE = "Role";
    private static final String TABLE_ORDERS = "Orders";
    private static final String TABLE_ORDER_ITEMS = "OrderItems";
    private static final String TABLE_CATETORIES = "Categories";
    private static final String TABLE_BOOK = "Book";
    private static final String COL_USERNAME = "username";
    private static final int DB_VERSION = 1;

    public DatabaseAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String queryUser = "CREATE TABLE " + TABLE_USER + " ( " +
                    " userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL, " +
                    "email TEXT UNIQUE, " +
                    "address TEXT, " +
                    "phone NUMERIC UNIQUE, " +
                    "roleId INTEGER NOT NULL DEFAULT 1)";
            String queryRole = "CREATE TABLE " + TABLE_ROLE + "(" +
                    "roleId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "roleName TEXT)";
            String queryOrder = "CREATE TABLE " + TABLE_ORDERS +
                    "( orderId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "orderDate TEXT, " +
                    "totalAmount INTEGER, " +
                    "userId INTEGER, FOREIGN KEY ('userId') REFERENCES " + TABLE_USER + "('userId'))";
            String queryCategories = "CREATE TABLE " + TABLE_CATETORIES +
                    "(categoriesId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "categoriesName TEXT)";
            String queryOrderItem = "CREATE TABLE " + TABLE_ORDER_ITEMS + "( orderItemId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "orderId INTEGER NOT NULL, "+
                    "bookId INTEGER NOT NULL, " +
                    "quantity INTEGER, "+
                    "price INTEGER, " +
                    "FOREIGN KEY ('orderId') REFERENCES " + TABLE_ORDERS + "('orderId'), " +
                    "FOREIGN KEY ('bookId') REFERENCES " + TABLE_ORDERS + "('bookId'))";
            String queryBook = "CREATE TABLE " + TABLE_BOOK +
                    "(bookId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "title TEXT NOT NULL, " +
                    "author TEXT NOT NULL, " +
                    "publisher TEXT NOT NULL, " +
                    "price REAL NOT NULL, " +
                    "quantity NUMERIC, " +
                    "image TEXT, " +
                    "description TEXT, " +
                    "rating REAL, " +
                    "pages INTEGER, " +
                    "dimension TEXT, " +
                    "categoriesId INTEGER, " +
                    "FOREIGN KEY('categoriesId') REFERENCES " + TABLE_CATETORIES + "('categoriesId'))";
            db.execSQL(queryOrderItem);
            db.execSQL(queryBook);
            db.execSQL(queryCategories);
            db.execSQL(queryOrder);
            db.execSQL(queryRole);
            db.execSQL(queryUser);
        } catch (Exception ex) {
            Log.e("Error", "Table exists ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @SuppressLint("Range")
    public List<User> getAllData() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USER, null,null, null,null,null,null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor and create YourDataModel objects
                User data = new User();
                data.setUsername(cursor.getString(cursor.getColumnIndex("username")));
                data.setPassword(cursor.getString(cursor.getColumnIndex("password")));
                // Set other properties of the data model

                userList.add(data);
            } while (cursor.moveToNext());

            cursor.close();
        }

        db.close();
        return userList;
    }


}

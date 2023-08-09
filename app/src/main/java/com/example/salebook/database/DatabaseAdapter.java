package com.example.salebook.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.salebook.MainActivity;
import com.example.salebook.R;
import com.example.salebook.adapter.UserAdapter;
import com.example.salebook.fragment.FragHome;
import com.example.salebook.fragment.FragSignIn;
import com.example.salebook.model.Role;
import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SaleBookDB.db";
    //    TABLE USER
    private static final String TABLE_USER = "User";
    private static final String COL_USER_ID = "userId";
    private static final String COL_USERNAME = "username";
    private static final String COL_PASSWORD = "password";
    private static final String COL_EMAIL = "email";
    private static final String COL_ADDRESS = "address";
    private static final String COL_PHONE = "phone";
    //    TABLE ROLE
    private static final String TABLE_ROLE = "Role";
    private static final String COL_ROLE_ID = "roleId";
    private static final String COL_ROLE_NAME = "roleName";
    //    TABLE ORDER
    private static final String TABLE_ORDERS = "Orders";
    private static final String COL_ORDER_ID = "orderId";
    private static final String COL_ORDER_DATE = "orderDate";
    private static final String COL_ORDER_TOTAL = "orderTotal";
    //    TABLE ORDER ITEMS
    private static final String TABLE_ORDER_ITEMS = "OrderItems";
    private static final String COL_ITEM_ID = "itemId";
    private static final String COL_ITEM_QUANTITY = "itemQuantity";
    private static final String COL_ITEM_DEBT = "itemDebt";
    //    TABLE CATEGORY
    private static final String TABLE_CATETORIES = "Categories";
    private static final String COL_CATE_ID = "categoriesId";
    private static final String COL_CATE_NAME = "categoriesName";
    //    TABLE BOOK
    private static final String TABLE_BOOK = "Book";
    private static final String COL_BOOK_ID = "bookId";
    private static final String COL_BOOK_TITLE = "bookTitle";
    private static final String COL_BOOK_AUTHOR = "bookAuthor";
    private static final String COL_BOOK_PUB = "bookPublisher";
    private static final String COL_BOOK_PRICE = "bookPrice";
    private static final String COL_BOOK_QUANTITY = "bookQuantity";
    private static final String COL_BOOK_IMG = "bookImage";
    private static final String COL_BOOK_DESC = "bookDescription";
    private static final String COL_BOOK_RATE = "bookRating";
    private static final String COL_BOOK_PAGE = "bookPage";
    private static final String COL_BOOK_DIMENS = "bookDimension";


    //    DB VERSION
    private static final int DB_VERSION = 1;

    public DatabaseAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            String queryUser = "CREATE TABLE " + TABLE_USER + " ( " + COL_USER_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_USERNAME +
                    " TEXT NOT NULL UNIQUE, " + COL_PASSWORD +
                    " TEXT NOT NULL, " + COL_EMAIL +
                    " TEXT UNIQUE, " + COL_ADDRESS +
                    " TEXT, " + COL_PHONE +
                    " NUMERIC UNIQUE, " + COL_ROLE_ID +
                    " INTEGER NOT NULL DEFAULT 1, FOREIGN KEY ( " + COL_USER_ID + " ) REFERENCES " + TABLE_ROLE + " (" + COL_ROLE_ID + ") )";
            String queryRole = "CREATE TABLE " + TABLE_ROLE + "(" + COL_ROLE_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ROLE_NAME +
                    " TEXT)";
            String queryOrder = "CREATE TABLE " + TABLE_ORDERS +
                    "( " + COL_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ORDER_DATE +
                    " TEXT, " + COL_ORDER_TOTAL +
                    " INTEGER, " + COL_USER_ID +
                    " INTEGER, FOREIGN KEY (" + COL_USER_ID + ") REFERENCES " + TABLE_USER + "(" + COL_USER_ID + "))";
            String queryCategories = "CREATE TABLE " + TABLE_CATETORIES +
                    "(" + COL_CATE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_CATE_NAME +
                    " TEXT)";
            String queryOrderItem = "CREATE TABLE " + TABLE_ORDER_ITEMS + "( " + COL_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ORDER_ID + " INTEGER NOT NULL, " + COL_BOOK_ID +
                    " INTEGER NOT NULL, " + COL_ITEM_QUANTITY +
                    " INTEGER, " + COL_ITEM_DEBT +
                    " INTEGER, " +
                    "FOREIGN KEY (" + COL_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COL_ORDER_ID + "), " +
                    "FOREIGN KEY (" + COL_BOOK_ID + ") REFERENCES " + TABLE_BOOK + "(" + COL_BOOK_ID + "))";
            String queryBook = "CREATE TABLE " + TABLE_BOOK +
                    "(" + COL_BOOK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_BOOK_TITLE +
                    " TEXT NOT NULL, " + COL_BOOK_AUTHOR +
                    " TEXT NOT NULL, " + COL_BOOK_PUB +
                    " TEXT NOT NULL, " + COL_BOOK_PRICE +
                    " REAL NOT NULL, " + COL_BOOK_QUANTITY +
                    " NUMERIC, " + COL_BOOK_IMG +
                    " TEXT, " + COL_BOOK_DESC +
                    " TEXT, " + COL_BOOK_RATE +
                    " REAL, " + COL_BOOK_PAGE +
                    " INTEGER, " + COL_BOOK_DIMENS +
                    " TEXT, " + COL_CATE_ID +
                    " INTEGER, " +
                    "FOREIGN KEY(" + COL_CATE_ID + ") REFERENCES " + TABLE_CATETORIES + "(" + COL_CATE_ID + "))";
            db.execSQL(queryOrderItem);
            db.execSQL(queryBook);
            db.execSQL(queryCategories);
            db.execSQL(queryOrder);
            db.execSQL(queryRole);
            db.execSQL(queryUser);
            defaultRole(db);
            accountAdmin(db);
        } catch (Exception ex) {
            Log.e("Error", "Table exists ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public void defaultRole(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COL_ROLE_NAME, "User");
        db.insert(TABLE_ROLE, null, values);

        values.clear();
        values.put(COL_ROLE_NAME, "Admin");
        db.insert(TABLE_ROLE, null, values);
    }

    public void accountAdmin(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(COL_USERNAME, "admin");
        values.put(COL_PASSWORD, "123");
        values.put(COL_ROLE_ID, 2);
        db.insert(TABLE_USER, null, values);
    }

    @SuppressLint("Range")
    public List<User> getAdminAccount() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COL_ROLE_ID + " = 2 ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor and create YourDataModel objects
                User data = new User();
                data.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
                data.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                // Set other properties of the data model
                userList.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return userList;
    }

    @SuppressLint("Range")
    public List<User> getAllData() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                // Retrieve data from the cursor and create YourDataModel objects
                User data = new User();
                data.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
                data.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                // Set other properties of the data model
                userList.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return userList;
    }

    @SuppressLint("Range")
    public User getUserByUsernameAndPassword(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT u.*, r.* FROM " + TABLE_USER +
                " u," + TABLE_ROLE +
                " r WHERE u." + COL_ROLE_ID + " = r." + COL_ROLE_ID +
                " AND " + COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";

        String[] selectionArgs = {username, password};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        User user = null;
        if (cursor != null && cursor.moveToFirst()) {
            // Create a new User object with data from the cursor
            user = new User();
            user.setUserId(cursor.getInt(cursor.getColumnIndex(COL_USER_ID)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));

            // Retrieve the role information and create a new Role object
            Role role = new Role();
            role.setRoleId(cursor.getInt(cursor.getColumnIndex(COL_ROLE_ID)));
            role.setRoleName(cursor.getString(cursor.getColumnIndex(COL_ROLE_NAME)));
            user.setRoleId(role);
            cursor.close();
        } else {
            cursor.close();
            db.close();
            return null;
        }
        db.close();
        return user;
    }

    public void deleteUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};

        // Perform the delete operation
        db.delete(TABLE_USER, selection, selectionArgs);

        db.close();
    }

    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);

        long newRow = db.insert(TABLE_USER, null, values);
        db.close();
        return newRow == -1 ? false : true;
    }

    public boolean updateUserInfo(String username, int newRole) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Define the WHERE clause
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};

        // Perform the update operation
        long state = db.update(TABLE_USER, values, selection, selectionArgs);
        db.close();
        if (state == -1) {
            return false;
        } else {
            return true;
        }
    }
}

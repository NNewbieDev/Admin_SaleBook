package com.example.salebook.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.salebook.model.Book;
import com.example.salebook.model.Category;
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
                    " INTEGER NOT NULL, " + COL_BOOK_QUANTITY +
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

    //      GET METHODS
    @SuppressLint("Range")
    public List<User> getAdminAccount() {
        List<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE " + COL_ROLE_ID + " = 2 ";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {

                User data = new User();
                data.setUsername(cursor.getString(cursor.getColumnIndex(COL_USERNAME)));
                data.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));

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
    public List<User> getUser() {
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

//                data.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
//                data.setPassword(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                int roleId = cursor.getInt(cursor.getColumnIndex(COL_ROLE_ID));

                // Tạo và gán đối tượng Role cho User
                Role role = new Role();
                role.setRoleId(roleId);
                data.setRoleId(role);

                // Set other properties of the data model
                userList.add(data);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return userList;
    }
    @SuppressLint("Range")
    public List<Book> getAllBook() {
        List<Book> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT b.*, c.* FROM " + TABLE_BOOK +
//                " b," + TABLE_CATETORIES +
//                " c WHERE b." + COL_CATE_NAME + " = c." + COL_CATE_NAME ;
        String query = "SELECT * FROM " + TABLE_BOOK;
        Cursor cursor = db.rawQuery(query, null);
        if (cursor != null && cursor.moveToFirst()) {
            do {
                Book book = new Book();
                book.setTitle(cursor.getString(cursor.getColumnIndex(COL_BOOK_TITLE)));
                book.setAuthor(cursor.getString(cursor.getColumnIndex(COL_BOOK_AUTHOR)));
                book.setPublisher(cursor.getString(cursor.getColumnIndex(COL_BOOK_PUB)));
                book.setImage(cursor.getString(cursor.getColumnIndex(COL_BOOK_IMG)));
                book.setDimension(cursor.getString(cursor.getColumnIndex(COL_BOOK_DIMENS)));
                book.setPrice(cursor.getInt(cursor.getColumnIndex(COL_BOOK_PRICE)));
                book.setPages(cursor.getInt(cursor.getColumnIndex(COL_BOOK_PAGE)));
                book.setQuantity(cursor.getInt(cursor.getColumnIndex(COL_BOOK_QUANTITY)));
                book.setRating(cursor.getDouble(cursor.getColumnIndex(COL_BOOK_RATE)));

                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(COL_CATE_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(COL_CATE_NAME)));

                book.setCategoriesId(category);
                bookList.add(book);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return bookList;
    }
    @SuppressLint("Range")
    public List<Category> getAllCate(){
        List<Category> categoryList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query =  "SELECT * FROM " + TABLE_CATETORIES;
        Cursor cursor = db.rawQuery(query, null);
        if(cursor != null && cursor.moveToFirst()){
            do {
                Category category = new Category();
                category.setName(cursor.getString(cursor.getColumnIndex(COL_CATE_NAME)));
                categoryList.add(category);
            }while(cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return categoryList;
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

    //    DELETE METHODS
    public void deleteUserByUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause
        String selection = COL_USERNAME + " = ?";
        String[] selectionArgs = {username};

        // Perform the delete operation
        db.delete(TABLE_USER, selection, selectionArgs);

        db.close();
    }

    public void deleteBookByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause
        String selection = COL_BOOK_TITLE + " = ?";
        String[] selectionArgs = {name};

        // Perform the delete operation
        db.delete(TABLE_BOOK, selection, selectionArgs);

        db.close();
    }

    public void deleteCateByName(String name) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the WHERE clause
        String selection = COL_CATE_NAME + " = ?";
        String[] selectionArgs = {name};

        // Perform the delete operation
        db.delete(TABLE_CATETORIES, selection, selectionArgs);

        db.close();
    }

    //    ADD METHODS
    public boolean addUser(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, username);
        values.put(COL_PASSWORD, password);

        long newRow = db.insert(TABLE_USER, null, values);
        db.close();
        return newRow == -1 ? false : true;
    }

    public void themUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_USERNAME, user.getUsername());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_ADDRESS, user.getAddress());
        values.put(COL_PHONE, user.getPhone());

        long newRowId = db.insert(TABLE_USER, null, values);
        db.close();
    }

    public boolean addCate(String nameCate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_CATE_NAME, nameCate);

        long newRow = db.insert(TABLE_CATETORIES, null, values);
        db.close();
        return newRow == -1 ? false : true;
    }

    public boolean addBook(String title, String author, String publisher, int price, int quantity,
                           String img, int pages, String dimension, Category category) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_BOOK_TITLE, title);
        values.put(COL_BOOK_AUTHOR, author);
        values.put(COL_BOOK_PUB, publisher);
        values.put(COL_BOOK_PRICE, price);
        values.put(COL_BOOK_QUANTITY, quantity);
        values.put(COL_BOOK_IMG, img);
        values.put(COL_BOOK_PAGE, pages);
        values.put(COL_BOOK_DIMENS, dimension);
        values.put(COL_CATE_ID, category.getId());

        long newRow = db.insert(TABLE_BOOK, null, values);
        db.close();
        return newRow == -1 ? false : true;
    }

    //    UPDATE METHODS
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

    public boolean updateBookInfo(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Define the WHERE clause
        String selection = COL_BOOK_TITLE + " = ?";
        String[] selectionArgs = {name};

        // Perform the update operation
        long state = db.update(TABLE_BOOK, values, selection, selectionArgs);
        db.close();
        if (state == -1) {
            return false;
        } else {
            return true;
        }
    }
}

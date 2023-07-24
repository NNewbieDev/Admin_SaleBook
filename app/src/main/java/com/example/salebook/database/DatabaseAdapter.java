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

public class DatabaseAdapter extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SaleBookDB.db";
    private static final String TABLE_USER = "User";
    private static final String COL_USERNAME = "username";
    private static final int DB_VERSION = 1;
    public DatabaseAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            String queryUser = "CREATE TABLE " + TABLE_USER + " ( " +
                    " userId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "username TEXT NOT NULL UNIQUE, " +
                    "password TEXT NOT NULL, " +
                    "email TEXT UNIQUE, " +
                    "address TEXT, " +
                    "phone NUMERIC UNIQUE, " +
                    "roleId INTEGER NOT NULL DEFAULT 1)";
            sqLiteDatabase.execSQL(queryUser);
        } catch (Exception ex){
            Log.e("Error", "Table exists " );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String queryDel = "DROP TABLE IF EXISTS " + TABLE_USER;
        sqLiteDatabase.execSQL(queryDel);
        onCreate(sqLiteDatabase);

    }

    public void add(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues content  = new ContentValues();
        content.put("username", username);
        content.put("password", password);
        db.insert(TABLE_USER, null, content);
        db.close();
    }

    public void read(){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {COL_USERNAME, "password"};
        String selection = COL_USERNAME + "=?";
        String[] selectionArgs = {"abc"};
        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_USERNAME));
            Toast.makeText(new FragSignIn().getContext(), name, Toast.LENGTH_SHORT).show();
        }
        cursor.close();
        db.close();
    }
}

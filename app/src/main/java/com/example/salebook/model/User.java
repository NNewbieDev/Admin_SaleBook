package com.example.salebook.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebook.AdminActivity;
import com.example.salebook.R;
import com.example.salebook.adapter.UserAdapter;
import com.example.salebook.database.DatabaseAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String username;
    private String password;
    private String email;
    private String address;
    private int phone;
    private Role roleId;
    public User() {

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void addUser(UserAdapter userAdapter, List<User> list, View view) {
        DatabaseAdapter adapter = new DatabaseAdapter(view.getContext());
        SQLiteDatabase db = adapter.getWritableDatabase();
        ContentValues values = new ContentValues();

        EditText txtUsername = view.findViewById(R.id.inputUsername);
        EditText txtPassword = view.findViewById(R.id.inputPassword);
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        values.put("username", username);
        values.put("password", password);

        long newRow = db.insert("User", null, values);

        if (newRow == -1) {
            Toast.makeText(view.getContext(), "Them khong thanh cong", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(view.getContext(), "Them thanh cong", Toast.LENGTH_SHORT).show();
        }
        db.close();
//        list = adapter.getAllData();
//        userAdapter.setData(list);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Role getRoleId() {
        return roleId;
    }

    public void setRoleId(Role roleId) {
        this.roleId = roleId;
    }
}

package com.example.salebook.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.AdminActivity;
import com.example.salebook.R;
import com.example.salebook.adapter.UserAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

public class FragHome extends Fragment {
    LinearLayout signInLayout;

    RecyclerView recyclerView;
    DatabaseAdapter db;
    List<User> userList;
    UserAdapter userAdapter;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.f_home, container, false);
        View layoutItem = inflater.inflate(R.layout.f_home_item, container, false);

        signInLayout = layoutItem.findViewById(R.id.accountUser);
        recyclerView = layout.findViewById(R.id.listViewAccount);

        db = new DatabaseAdapter(layout.getContext());

        userAdapter = new UserAdapter();
        userList = db.getAllData();
        userAdapter.setData(userList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);


        return layout;
    }


    public void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        View dialogView = getLayoutInflater().inflate(R.layout.form_sign_in, null);
        builder.setView(dialogView);

        EditText ipUsername = dialogView.findViewById(R.id.inputUsername);
        EditText ipPassword = dialogView.findViewById(R.id.inputPassword);

        Button signInBtn = dialogView.findViewById(R.id.signInBtn);
        signInBtn.setOnClickListener(view -> {
            String msg = "";

            Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
        });

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}



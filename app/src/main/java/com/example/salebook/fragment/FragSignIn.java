package com.example.salebook.fragment;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;

public class FragSignIn extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =  inflater.inflate(R.layout.form_sign_in, container, false);
        Button signInBtn = layout.findViewById(R.id.signInBtn);
        EditText username = layout.findViewById(R.id.inputUsername);
        EditText password = layout.findViewById(R.id.inputPassword);
        signInBtn.setOnClickListener(view -> {
            DatabaseAdapter db = new DatabaseAdapter(this.getContext());
            db.add(username.getText().toString(), password.getText().toString());
//            db.read();
        });

        return layout;
    }
}

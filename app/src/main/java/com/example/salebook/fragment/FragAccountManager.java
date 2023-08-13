package com.example.salebook.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.adapter.UserAdapter;
import com.example.salebook.adapter.UserManagerAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.List;

public class FragAccountManager extends Fragment {
    private RecyclerView listAccountManager;
    private DatabaseAdapter db;
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText confirmPassword;
    private ConstraintLayout btnAdd;
    private UserManagerAdapter userManagerAdapter;
    private List<User> userList;
    private Button signUpBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.f_account_manager, container, false);

        listAccountManager = layout.findViewById(R.id.listAccountManager);

        userManagerAdapter = new UserManagerAdapter();
        db = new DatabaseAdapter(layout.getContext());
        userList = db.getAllData();

        userManagerAdapter.setData(userList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout.getContext());
        listAccountManager.setLayoutManager(linearLayoutManager);
        listAccountManager.setAdapter(userManagerAdapter);

        btnAdd = layout.findViewById(R.id.btnAddAccount);

        btnAdd.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            View dialogView = getLayoutInflater().inflate(R.layout.form_sign_up, null);
            builder.setView(dialogView);

            inputUsername = dialogView.findViewById(R.id.inputUsername);
            inputPassword = dialogView.findViewById(R.id.inputPassword);
            confirmPassword = dialogView.findViewById(R.id.confirmPassword);
            signUpBtn = dialogView.findViewById(R.id.signUpBtn);

            signUpBtn.setOnClickListener(view1 -> {
                String username = inputUsername.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                String confirm = confirmPassword.getText().toString().trim();
                if (username.equals("") || password.equals("") || confirm.equals("")) {
                    Toast.makeText(layout.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                } else if (!confirm.equals(password)) {
                    Toast.makeText(layout.getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    if(db.addUser(username, password)){
                        Toast.makeText(layout.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(layout.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    userList.add(new User(username, password));

                }
            });
            // Create and show the dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        return layout;
    }
}

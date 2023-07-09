package com.example.admin.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.admin.MainActivity;
import com.example.admin.R;

public class FragHome extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layout = inflater.inflate(R.layout.f_home, container, false);
        LinearLayout signInLayout = layout.findViewById(R.id.accountUser);
        signInLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });

        return layout;
    }

    public void showLoginDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//        builder.setTitle("Đăng nhập");

        // Inflate the layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.form_sign_in, null);
        builder.setView(dialogView);

        // Get references to the input fields
//        EditText usernameEditText = dialogView.findViewById(R.id.username_edittext);
//        EditText passwordEditText = dialogView.findViewById(R.id.password_edittext);

        // Set up the buttons
//            builder.setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    // Perform login action here using the entered username and password
//                    String username = usernameEditText.getText().toString();
//                    String password = passwordEditText.getText().toString();
//                    login(username, password);
//                }
//            });
//            builder.setNegativeButton("Hủy", null);

        // Create and show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}



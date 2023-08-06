package com.example.salebook.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.salebook.R;
import com.example.salebook.adapter.UserAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.List;

public class FragSignUp extends Fragment {

    private Button signUpBtn;
    private EditText inputUsername;
    private EditText inputPassword;
    private EditText confirmPassword;
    private DatabaseAdapter db;
    private UserAdapter userAdapter;
    private List<User> userList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.form_sign_up, container, false);

        signUpBtn = layout.findViewById(R.id.signUpBtn);
        inputUsername = layout.findViewById(R.id.inputUsername);
        inputPassword = layout.findViewById(R.id.inputPassword);
        confirmPassword = layout.findViewById(R.id.confirmPassword);

        signUpBtn.setOnClickListener(view -> {
            String username = inputUsername.getText().toString().trim();
            String password = inputPassword.getText().toString().trim();
            String confirm = confirmPassword.getText().toString().trim();

            if(username.equals("") || password.equals("") || confirm.equals("")){
                Toast.makeText(layout.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
            }else if(!confirm.equals(password)){
                Toast.makeText(layout.getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
            }else {
                User newUser = new User();
                newUser.addUser(userAdapter, userList, layout);

            }
        });
        return layout;
    }
}

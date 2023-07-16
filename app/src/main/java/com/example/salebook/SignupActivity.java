package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etCfPassword;
    private EditText etPhoneNumber;
    private EditText etAddress;
    private CheckBox cbShowPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                if(etUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (etCfPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng xác nhận mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (etPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                } else if (etAddress.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    public void AnhXa(){
        btnLogin = (Button)findViewById(R.id.btn_login);
        btnRegister = (Button)findViewById(R.id.btn_register);
        etUserName = (EditText) findViewById(R.id.et_user_name);
        etPassword = (EditText) findViewById(R.id.et_password);
        etCfPassword = (EditText) findViewById(R.id.et_cf_password);
        etPhoneNumber = (EditText) findViewById(R.id.et_phone_number);
        etAddress = (EditText) findViewById(R.id.et_address);
    }
}
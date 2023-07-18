package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Button btnRegister;
    private CheckBox cbShowPassword;
    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();
        //xử lý sự kiện khi nhấn vào biểu tượng shop trên toolbar
        ImageView ivShop = findViewById(R.id.iv_shop);
        OnClickHelper onClickHelper = new OnClickHelper(this);
        ivShop.setOnClickListener(onClickHelper);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        // Sự kiện bật/tắt chế độ hiển thị mật khẩu
        cbShowPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    // Hiện mật khẩu
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // Ẩn mật khẩu
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                // Đảm bảo hiển thị lại văn bản đã nhập
                etPassword.setSelection(etPassword.getText().length());
            }
        });

    }
    public void Anhxa(){
        btnRegister = (Button)findViewById(R.id.buttonRegister);
        cbShowPassword = (CheckBox) findViewById(R.id.cb_show_password);
        etUsername = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etPassword = (EditText) findViewById(R.id.editTextTextPassword);

    }
}
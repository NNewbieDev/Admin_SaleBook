package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.List;

public class SignupActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnRegister;
    private EditText etUserName;
    private EditText etPassword;
    private EditText etCfPassword;
    private EditText etPhoneNumber;
    private EditText etAddress;
    private CheckBox cbShowPassword;
    private DatabaseAdapter databaseAdapter;
    private User user = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        AnhXa();
        databaseAdapter = new DatabaseAdapter(this);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
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
                    etCfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // Ẩn mật khẩu
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etCfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }

                // Đảm bảo hiển thị lại văn bản đã nhập
                etPassword.setSelection(etPassword.getText().length());
                etCfPassword.setSelection(etCfPassword.getText().length());
            }
        });
        //xử lý sự kiện khi nhấn vào biểu tượng shop trên toolbar
        ImageView ivShop = findViewById(R.id.iv_shop);
        OnClickHelper onClickHelper = new OnClickHelper(this);
        ivShop.setOnClickListener(onClickHelper);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                if(etUserName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập tên đăng nhập!", Toast.LENGTH_SHORT).show();
                } else if (!checkUserName(etUserName.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                } else if (etPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (etCfPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng xác nhận mật khẩu!", Toast.LENGTH_SHORT).show();
                } else if (!etCfPassword.getText().toString().equals(etPassword.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Xác nhận mật khẩu không đúng!", Toast.LENGTH_SHORT).show();
                    etCfPassword.setText("");
                } else if (etPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập số điện thoại!", Toast.LENGTH_SHORT).show();
                } else if (etAddress.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Vui lòng nhập địa chỉ!", Toast.LENGTH_SHORT).show();
                } else {
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công!", Toast.LENGTH_SHORT).show();
                    user.setUsername(etUserName.getText().toString());
                    user.setPassword(etPassword.getText().toString());
                    user.setAddress(etAddress.getText().toString());
                    user.setPhone(etPhoneNumber.getText().toString());
                    user.setEmail(null);
                    databaseAdapter.themUser(user);

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
        cbShowPassword = (CheckBox) findViewById(R.id.cb_show_password);
    }
    public boolean checkUserName(String username){
        List<User> userList = databaseAdapter.getAllData();
        for (User u : userList) {
            if (username.equals(u.getUsername().toString())) {
                return false;
            }

        }
        return true;
    }
}
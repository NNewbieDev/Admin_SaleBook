package com.example.salebook;


import static com.example.salebook.R.id.iv_shop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.OrderItem;
import com.example.salebook.model.Role;
import com.example.salebook.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button btnRegister;
    private Button btnLogin;
    private CheckBox cbShowPassword;
    private EditText etUsername;
    private EditText etPassword;
    private DatabaseAdapter databaseAdapter;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Anhxa();

        databaseAdapter = new DatabaseAdapter(this);

        //xử lý sự kiện khi nhấn vào biểu tượng shop trên toolbar
        ImageView ivShop = findViewById(iv_shop);
        OnClickHelper onClickHelper = new OnClickHelper(this);
        ivShop.setOnClickListener(onClickHelper);

        //xử lý sự kiện khi nhấn thanh 3 gạch

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageView imvNavigation = findViewById(R.id.imv_navigation);

        XuLyThanhTruot xuLyThanhTruot = new XuLyThanhTruot(drawerLayout, imvNavigation);
        xuLyThanhTruot.xuLy();



        //xử lý sự kiện thanh trượt


        SuKienThanhTruot suKienThanhTruot = new SuKienThanhTruot(this, imvTrangChu, imvInfoUser);
        suKienThanhTruot.xuLy();






        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
        //Xử lý sự kiện nút đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = etUsername.getText().toString();
                String pass = etPassword.getText().toString();
                user = checkLogin(userName, pass);
                if (user != null) {
                    UserManager.getInstance().setCurrentUser(user);
                    // Tiếp tục xử lý sau khi đăng nhập thành công
                }

                if (checkLogin(userName, pass) == null)
                    Toast.makeText(getApplicationContext(), "Tên tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                else if (checkLogin(userName, pass).getRoleId().getRoleId() == 1) {


                    Intent intent = new Intent(MainActivity.this, SalesActivity.class);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, AdminActivity.class);

                    startActivity(intent);
                }
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

//    public int checkLogin(String user, String pasword) {
//        List<User> userList = databaseAdapter.getUser();
//        for (User u : userList) {
//            if (user.equals(u.getUsername().toString()) && pasword.equals(u.getPassword().toString()) &&
//                    u.getRoleId().getRoleId() == 2) {
//                return 2;
//            } else if (user.equals(u.getUsername().toString()) && pasword.equals(u.getPassword().toString())) {
//                return 1;
//            }
//
//        }
//        return 0;
//    }

    public User checkLogin(String user, String pasword) {
        List<User> userList = databaseAdapter.getUser();
        for (User u : userList) {
            if (user.equals(u.getUsername().toString()) && pasword.equals(u.getPassword().toString())) {
                return u;
            }
        }
        return null;
    }

    public void Anhxa() {
        btnRegister = (Button) findViewById(R.id.buttonRegister);
        cbShowPassword = (CheckBox) findViewById(R.id.cb_show_password);
        etUsername = (EditText) findViewById(R.id.editTextTextEmailAddress);
        etPassword = (EditText) findViewById(R.id.editTextTextPassword);
        btnLogin = (Button) findViewById(R.id.btn_login);
        imvTrangChu = findViewById(R.id.trangchu);
        imvInfoUser = findViewById(R.id.statistic);

//        databaseAdapter = new DatabaseAdapter(this);
//        List<OrderItem> orderItemsList = databaseAdapter.getOrderItem();
//        for (OrderItem o : orderItemsList) {
//            Log.d("Tag", "Giá trị bảng OrderItems: " + o.getBookId().getBookId());
//        }
//        databaseAdapter.close();

    }
}
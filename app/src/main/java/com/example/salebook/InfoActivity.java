package com.example.salebook;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.List;

public class InfoActivity extends AppCompatActivity {

    private TextView tvUserEdit;
    private TextView tvAddressEdit;
    private TextView tvPhoneNumberEdit;
    private ImageView imvEditUser;
    private ImageView imvEditAddress;
    private ImageView imvEditPhoneNumber;
    private Button btnApply;
    private Button btnLogout;
    private DatabaseAdapter databaseAdapter;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Anhxa();

        //xử lý sự kiện khi nhấn thanh 3 gạch

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageView imvNavigation = findViewById(R.id.imv_navigation);

        XuLyThanhTruot xuLyThanhTruot = new XuLyThanhTruot(drawerLayout, imvNavigation);
        xuLyThanhTruot.xuLy();
        //xử lý sự kiện thanh trượt

        SuKienThanhTruot suKienThanhTruot = new SuKienThanhTruot(this, imvTrangChu, imvInfoUser);
        suKienThanhTruot.xuLy();

        databaseAdapter = new DatabaseAdapter(this);


        User currentUser = UserManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            // Sử dụng thông tin người dùng, ví dụ: currentUser.getUsername(), currentUser.getRoleId(),...
            tvUserEdit.setText(currentUser.getUsername());
            tvAddressEdit.setText(currentUser.getAddress());
            tvPhoneNumberEdit.setText("0" + currentUser.getPhone());
        }


        imvEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextDialog("Thay đổi tên người dùng", tvUserEdit.getText().toString(), "username");
            }

        });

        imvEditAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextDialog("Thay đổi địa chỉ", tvAddressEdit.getText().toString(), "address");
            }
        });

        imvEditPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showEditTextDialog("Thay đổi số điện thoại", tvPhoneNumberEdit.getText().toString(), "phone");
            }
        });

        btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkUserName(tvUserEdit.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                }
                else {
                    currentUser.setUsername(tvUserEdit.getText().toString());
                    currentUser.setAddress(tvAddressEdit.getText().toString());
                    currentUser.setPhone(tvPhoneNumberEdit.getText().toString());
                    databaseAdapter.updateUser(currentUser);
                    Toast.makeText(getApplicationContext(), "Áp dụng thành công!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManager.getInstance().logout();
                Intent intent = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
    private void showEditTextDialog(String title, String defaultValue, final String field) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText editText = new EditText(this);
        editText.setText(defaultValue);
        builder.setView(editText);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newValue = editText.getText().toString();
                if (field.equals("username")) {
                    tvUserEdit.setText(newValue);
                } else if (field.equals("address")) {
                    tvAddressEdit.setText(newValue);
                } else if (field.equals("phone")) {
                    tvPhoneNumberEdit.setText(newValue);
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    private void Anhxa() {
        tvUserEdit = findViewById(R.id.tv_user_edit);
        tvAddressEdit = findViewById(R.id.tv_address_edit);
        tvPhoneNumberEdit = findViewById(R.id.tv_phone_number_edit);
        imvEditUser = findViewById(R.id.iv_edit_user);
        imvEditAddress = findViewById(R.id.iv_edit_address);
        imvEditPhoneNumber = findViewById(R.id.iv_edit_phone_number);
        btnApply = findViewById(R.id.btn_apply);
        btnLogout = findViewById(R.id.btn_logout);
        imvTrangChu = findViewById(R.id.trangchu);
        imvInfoUser = findViewById(R.id.statistic);



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
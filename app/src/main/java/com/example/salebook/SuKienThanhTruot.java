package com.example.salebook;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.salebook.model.User;

public class SuKienThanhTruot {
    private Context context;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;

    public SuKienThanhTruot(Context context, ImageView imvTrangChu, ImageView imvInfoUser) {
        this.context = context;
        this.imvTrangChu = imvTrangChu;
        this.imvInfoUser = imvInfoUser;
    }

//    public SuKienThanhTruot(Context context, ImageView imvTrangChu, ImageView imvInfoUser, User user) {
//        this.context = context;
//        this.imvTrangChu = imvTrangChu;
//        this.imvInfoUser = imvInfoUser;
//        this.user = user;  // Lưu thông tin người dùng
//    }

    public void xuLy(){
        imvTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SalesActivity.class);
                context.startActivity(intent);

            }
        });
        imvInfoUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Trong bất kỳ activity nào
                User currentUser = UserManager.getInstance().getCurrentUser();
                if (currentUser != null) {
                    // Thực hiện xử lý liên quan đến người dùng
                    Intent intent = new Intent(context, InfoActivity.class);
                    context.startActivity(intent);
                } else {
                    // Người dùng chưa đăng nhập, thực hiện xử lý tương ứng
                    Intent intent = new Intent(context, MainActivity.class);
                    context.startActivity(intent);
                }
            }
        });
    }
}

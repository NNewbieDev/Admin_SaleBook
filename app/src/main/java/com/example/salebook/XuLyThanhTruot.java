package com.example.salebook;

import android.view.View;
import android.widget.ImageView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class XuLyThanhTruot {
    private DrawerLayout drawerLayout;
    private ImageView imvNavigation;

    public XuLyThanhTruot(DrawerLayout drawerLayout, ImageView imvNavigation) {
        this.drawerLayout = drawerLayout;
        this.imvNavigation = imvNavigation;
    }

    public void xuLy() {
        imvNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }
}

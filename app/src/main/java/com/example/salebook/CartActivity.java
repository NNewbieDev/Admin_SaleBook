package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.salebook.adapter.OrderItemAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.OrderItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCar;
    private OrderItemAdapter orderItemAdapter;
    private DatabaseAdapter databaseAdapter;
    private List<OrderItem> listOrderItem;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        //xử lý sự kiện khi nhấn thanh 3 gạch

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ImageView imvNavigation = findViewById(R.id.imv_navigation);
        imvTrangChu = findViewById(R.id.trangchu);
        imvInfoUser = findViewById(R.id.statistic);

        XuLyThanhTruot xuLyThanhTruot = new XuLyThanhTruot(drawerLayout, imvNavigation);
        xuLyThanhTruot.xuLy();

        //xử lý sự kiện thanh trượt

        SuKienThanhTruot suKienThanhTruot = new SuKienThanhTruot(this, imvTrangChu, imvInfoUser);
        suKienThanhTruot.xuLy();

        recyclerViewCar = findViewById(R.id.rec_car);

        orderItemAdapter = new OrderItemAdapter();
        databaseAdapter = new DatabaseAdapter(this);
        listOrderItem = databaseAdapter.getOrderItem();

        orderItemAdapter.setData(listOrderItem);
//
        //---- Grid ----
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
//        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
//        recyclerViewCar.setLayoutManager(gridLayoutManager);
        recyclerViewCar.setAdapter(orderItemAdapter);
    }
}
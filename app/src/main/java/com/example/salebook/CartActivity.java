package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.salebook.adapter.OrderItemAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCar;
    private OrderItemAdapter orderItemAdapter;
    private DatabaseAdapter databaseAdapter;
    private List<OrderItem> listOrderItem;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;
    private TextView tvTotalMoney;
    private TextView tvTotalQuantity;

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


        databaseAdapter = new DatabaseAdapter(this);
        listOrderItem = databaseAdapter.getOrderItem();
        databaseAdapter.close();
        orderItemAdapter = new OrderItemAdapter((ArrayList<OrderItem>) listOrderItem, this);

        orderItemAdapter.setData(listOrderItem);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewCar.setLayoutManager(linearLayoutManager);
        recyclerViewCar.setAdapter(orderItemAdapter);

        tvTotalQuantity =findViewById(R.id.tv_tongsoluong);
        tvTotalMoney = findViewById(R.id.tv_total_money);
        int quantity = 0;
        int price = 0;
        for (OrderItem o: listOrderItem) {
            quantity += o.getQuantity();
            price += o.getQuantity()*o.getBookId().getPrice();

        }
        tvTotalQuantity.setText(quantity+" ");
        tvTotalMoney.setText(price + "đ");
        orderItemAdapter.setQuantityChangeListener(new OrderItemAdapter.QuantityChangeListener() {
            @Override
            public void onQuantityChanged() {
                // Do something when the overall quantity changes

            }

            @Override
            public void onQuantityChangedForPosition(int position) {
                // Do something when the quantity of a specific item changes
                // Update the total price based on the changed item's position
                //int newPrice = listOrderItem.get(position).getQuantity() * listOrderItem.get(position).getBookId().getPrice();
                int newPrice = 0;
                int newQuantity = 0;
                for (OrderItem o: listOrderItem) {
                    newQuantity +=o.getQuantity();
                    newPrice += o.getQuantity()*o.getBookId().getPrice();

                }
                tvTotalQuantity.setText(newQuantity+" ");
                tvTotalMoney.setText(newPrice + "đ");
            }
        });
    }
}
package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.salebook.adapter.OrderItemAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.OrderItem;

import java.util.List;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCar;
    private OrderItemAdapter orderItemAdapter;
    private DatabaseAdapter databaseAdapter;
    private List<OrderItem> listOrderItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

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
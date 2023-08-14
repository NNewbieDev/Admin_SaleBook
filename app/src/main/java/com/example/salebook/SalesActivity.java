package com.example.salebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.salebook.adapter.ProductAdapter;
import com.example.salebook.adapter.UserManagerAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    private RecyclerView rclProductList;
    private ProductAdapter productAdapter;
    private DatabaseAdapter db;
    private List<Book> productlist;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        rclProductList = findViewById(R.id.rclproductlist);

        productAdapter = new ProductAdapter();
        db = new DatabaseAdapter(this);
        productlist = db.getDataBook();

        productAdapter.setData(productlist);
//
        //---- Grid ----
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rclProductList.setLayoutManager(gridLayoutManager);
        rclProductList.setAdapter(productAdapter);
//



    }


}
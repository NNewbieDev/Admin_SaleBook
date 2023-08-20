package com.example.salebook;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.salebook.adapter.ProductAdapter;

import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    private RecyclerView rclProductList;
    private ProductAdapter productAdapter;
    private DatabaseAdapter db;
    private List<Book> productlist;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        rclProductList = findViewById(R.id.rclproductlist);



        searchView = findViewById(R.id.searhview);
        searchView.clearFocus();


        productAdapter = new ProductAdapter();
        db = new DatabaseAdapter(this);
        productlist = db.getDataBook();
        productAdapter.setData(this,productlist);
        //---- Grid ----
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        rclProductList.setLayoutManager(gridLayoutManager);
        rclProductList.setAdapter(productAdapter);

        // Su Kien Tim Kiem
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return false;
            }
        });
    }

    private void searchList(String text) {
        List<Book> searchlist = new ArrayList<>();
        for(Book book : productlist){
            if(book.getTitle().toLowerCase().contains(text.toLowerCase())){
                searchlist.add(book);
            }
        }
        if(searchlist.isEmpty()){
            Toast.makeText(this,"Khong tim thay san pham",Toast.LENGTH_SHORT).show();

        }else {
            productAdapter.setData(this,searchlist);
        }
    }




}
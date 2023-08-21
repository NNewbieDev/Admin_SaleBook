package com.example.salebook;


import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;




import android.widget.ImageView;
import android.widget.Toast;

import com.example.salebook.adapter.ProductAdapter;

import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;
import com.example.salebook.model.Category;
import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

public class SalesActivity extends AppCompatActivity {
    private RecyclerView rclProductList;
    private ProductAdapter productAdapter;
    private DatabaseAdapter db;
    private List<Book> productlist;
    private SearchView searchView;
    private ImageView imvTrangChu;
    private ImageView imvInfoUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);


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

        //xử lý sự kiện khi nhấn vào biểu tượng shop trên toolbar
        ImageView ivShop = findViewById(R.id.iv_shop);
        OnClickHelper onClickHelper = new OnClickHelper(this);
        ivShop.setOnClickListener(onClickHelper);
        rclProductList = findViewById(R.id.rclproductlist);

        searchView = findViewById(R.id.searhview);
        searchView.clearFocus();


        productAdapter = new ProductAdapter();
        db = new DatabaseAdapter(this);
        productlist = db.getDataBook();
        productAdapter.setData(this, productlist);
        //---- Grid ----
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
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
        for (Book book : productlist) {
            if (book.getTitle().toLowerCase().contains(text.toLowerCase())) {
                searchlist.add(book);
            }
        }
        if (searchlist.isEmpty()) {
            Toast.makeText(this, "Khong tim thay san pham", Toast.LENGTH_SHORT).show();

        } else {
            productAdapter.setData(this, searchlist);
        }
    }
}




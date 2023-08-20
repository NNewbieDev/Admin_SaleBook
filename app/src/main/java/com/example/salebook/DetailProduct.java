package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.salebook.adapter.ProductAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;

import java.util.List;

public class    DetailProduct extends AppCompatActivity {
    private DatabaseAdapter db;
    private ProductAdapter productAdapter;
    private List<Book> productlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        TextView txtDetailTitle = findViewById(R.id.txt_detail_title);
        TextView txtDetailPrice = findViewById(R.id.txt_detail_price);
        TextView txtDetailProduct = findViewById(R.id.txtdetailproduct);
        Button btnAddProduct = findViewById(R.id.btnAddproduct);

        productAdapter = new ProductAdapter();
        db = new DatabaseAdapter(this);
        productlist = db.getDataBook();
        productAdapter.setData(this,productlist);

        Bundle bundle = getIntent().getExtras();
        if(bundle!= null){

            Book book = (Book) bundle.get("object_product");
            txtDetailTitle.setText(book.getTitle());
            txtDetailPrice.setText(String.valueOf(book.getPrice()));
            txtDetailProduct.setText(book.getDescription());

        }



//        Intent intent = getIntent();
//        if(intent != null){
//            Book book = (Book) intent.getSerializableExtra("object_product");
//            if(book!=null){
//                txtDetailTitle.setText(book.getTitle());
//                txtDetailPrice.setText(String.valueOf(book.getPrice()));
//
//            }
//        }


    }
}
package com.example.salebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.salebook.adapter.ProductAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetailProduct extends AppCompatActivity {
    private DatabaseAdapter db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ImageView imgDetail = findViewById(R.id.img_product);
        TextView txtDetailTitle = findViewById(R.id.txt_detail_title);
        TextView txtDetailPrice = findViewById(R.id.txt_detail_price);
        TextView txtDetailProduct = findViewById(R.id.txtdetailproduct);
        Button btnAddProduct = findViewById(R.id.btnAddproduct);
        db = new DatabaseAdapter(this);


        Bundle bundle = getIntent().getExtras();
        if(bundle== null){
            return;
        }
            Book book = (Book) bundle.get("object_product");
            txtDetailTitle.setText(book.getTitle());
        NumberFormat format = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        txtDetailPrice.setText(format.format(book.getPrice()));
            txtDetailProduct.setText(book.getDescription());
        Glide.with(this)
                .load(book.getImage()) // Đường dẫn ảnh từ đối tượng Book
                .into(imgDetail); // ImageView để hiển thị hình ảnh

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailProduct.this, CartActivity.class);
                db.addOrderItem(1, book.getBookId(), 1, 50);
                db.close();
                startActivity(intent);
            }
        });



    }
}
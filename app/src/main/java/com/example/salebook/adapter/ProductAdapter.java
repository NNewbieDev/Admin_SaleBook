package com.example.salebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salebook.DetailProduct;
import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Book> listProduct;
    private DatabaseAdapter db;
    private Context context;


    public ProductAdapter() {
    }
    public void setData(Context context,List<Book> listProduct){
        this.context=context;
        this.listProduct= listProduct;
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Chuyển dữ liệu list vào các phần tử
         Book book = listProduct.get(position);
        if (listProduct == null) {
            return;
        }
        holder.txttitle.setText(book.getTitle());
        double price = book.getPrice();
        String formattedPrice = formatPrice(price);
        holder.txtprice.setText(formattedPrice);
        // Thư Viện Gilde
        Glide.with(context)
                .load(book.getImage())
                .into(holder.imgproduct);
        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(book);

            }
        });

    }
    public void release(){
        context = null;
    }
    private String formatPrice(double price){
        NumberFormat format =NumberFormat.getCurrencyInstance(new Locale("vi","VN"));
        return format.format(price);
    }

    private void onClickGoToDetail(Book book) {
        Intent intent = new Intent(context, DetailProduct.class );
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_product",book);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public CardView layoutItem;
        public TextView txttitle, txtprice;

                public ImageView imgproduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.txt_title);
            txtprice = itemView.findViewById(R.id.txt_price);

            layoutItem = itemView.findViewById(R.id.layout_item);

            imgproduct = itemView.findViewById(R.id.img_product);
        }
    }
}

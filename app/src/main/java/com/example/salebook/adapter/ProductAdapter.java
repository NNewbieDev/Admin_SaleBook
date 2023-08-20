package com.example.salebook.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.DetailProduct;
import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;
import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

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
         Book book = listProduct.get(position);
        if (listProduct == null) {
            return;
        }
        holder.txttitle.setText(book.getTitle());
        holder.txtprice.setText(String.valueOf(book.getPrice()));

        holder.layoutItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickGoToDetail(book);

            }
        });
//        holder.imgproduct.setImageResource(listProduct.get(position).getImage());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });


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
        public Button btndetail;
        //        public ImageView imgproduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.txt_title);
            txtprice = itemView.findViewById(R.id.txt_price);
            btndetail = itemView.findViewById(R.id.btndetail);
            layoutItem = itemView.findViewById(R.id.layout_item);

//            imgproduct = itemView.findViewById(R.id.img_product);
        }
    }
}

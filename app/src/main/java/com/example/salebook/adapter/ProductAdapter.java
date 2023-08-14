package com.example.salebook.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
    public void setData(List<Book> list){
        this.listProduct= list;
        notifyDataSetChanged();

    }


    public ProductAdapter(ArrayList<Book> listProduct, Context context) {
        this.listProduct = listProduct;
        this.context = context;
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
//        holder.imgproduct.setImageResource(listProduct.get(position).getImage());
        holder.btndetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txttitle, txtprice;
        public Button btndetail;
//        public ImageView imgproduct;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txttitle=itemView.findViewById(R.id.txt_title);
            txtprice = itemView.findViewById(R.id.txt_price);
            btndetail = itemView.findViewById(R.id.btndetail);
//            imgproduct = itemView.findViewById(R.id.img_product);
        }
    }
}
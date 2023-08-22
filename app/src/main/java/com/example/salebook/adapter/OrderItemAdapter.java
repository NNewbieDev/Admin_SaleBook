package com.example.salebook.adapter;

import android.content.Context;
import android.util.Log;
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
import com.example.salebook.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHoler> {


    private List<OrderItem> listOrderItem;
    private DatabaseAdapter db;
    private Context context;

    public OrderItemAdapter(){

    }
    public void setData(List<OrderItem> list){
        this.listOrderItem= list;
        notifyDataSetChanged();

    }
    public OrderItemAdapter(ArrayList<OrderItem> listOrderItem, Context context) {
        this.listOrderItem = listOrderItem;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemAdapter.ViewHoler holder, int position) {
        OrderItem orderItem = listOrderItem.get(position);

        if (orderItem == null || orderItem.getBookId() == null) {
            // Không thể truy cập thông tin, xử lý tùy theo trường hợp
            return;
        }
        //Log.d("Tag", "Giá sách: " +  orderItem.getBookId().getTitle());

        holder.tvPrice.setText(String.valueOf(orderItem.getBookId().getPrice()));
        holder.tvInfo.setText(orderItem.getBookId().getTitle());


    }

    @Override
    public int getItemCount() {
        return listOrderItem.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        private TextView tvInfo;
        private TextView tvPrice;
        private TextView tvSoLuong;
        private ImageView imvBook;
        private Button btnTang;
        private Button btnGiam;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvPrice = itemView.findViewById(R.id.tv_price);
            //tvSoLuong = itemView.findViewById(R.id.tv_soluong);
            imvBook = itemView.findViewById(R.id.imv_book);
            //btnTang = itemView.findViewById(R.id.btn_tang);
            //btnGiam = itemView.findViewById(R.id.btn_giam);



        }
    }
}

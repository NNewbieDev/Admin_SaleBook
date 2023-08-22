package com.example.salebook.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemAdapter extends RecyclerView.Adapter<OrderItemAdapter.ViewHolder> {

    private List<OrderItem> listOrderItem;
    private DatabaseAdapter db;
    private Context context;
    private QuantityChangeListener onQuantityChanged;

    public OrderItemAdapter(ArrayList<OrderItem> listOrderItem, Context context) {
        this.listOrderItem = listOrderItem;
        this.context = context;
        this.db = new DatabaseAdapter(context);
    }

    public void setData(List<OrderItem> list) {
        this.listOrderItem = list;
        notifyDataSetChanged();
    }

    public void setQuantityChangeListener(QuantityChangeListener listener) {
        this.onQuantityChanged = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listOrderItem.size();
    }

    public interface QuantityChangeListener {
        void onQuantityChanged();
        void onQuantityChangedForPosition(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvInfo;
        private TextView tvPrice;
        private ImageView imvBook;
        private EditText etSoLuong;
        private TextWatcher textWatcher;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tv_info);
            tvPrice = itemView.findViewById(R.id.tv_price);
            etSoLuong = itemView.findViewById(R.id.et_soluong);
            imvBook = itemView.findViewById(R.id.imv_book);


        }

        public void bind(int position) {
            OrderItem orderItem = listOrderItem.get(position);

            if (orderItem == null || orderItem.getBookId() == null) {
                // Handle the case when orderItem or its bookId is null
                return;
            }

            tvPrice.setText(String.valueOf(orderItem.getBookId().getPrice()) + "đ");
            tvInfo.setText(orderItem.getBookId().getTitle());
            etSoLuong.removeTextChangedListener(textWatcher);
            etSoLuong.setText(String.valueOf(orderItem.getQuantity()));
            Glide.with(context)
                    .load(orderItem.getBookId().getImage())
                    .into(imvBook);

            textWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    int newQuantity = 0;
                    try {
                        newQuantity = Integer.parseInt(s.toString());
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    orderItem.setQuantity(newQuantity);

                    db.updateOrderItem(orderItem);

                    if (onQuantityChanged != null) {
                        onQuantityChanged.onQuantityChanged();
                        onQuantityChanged.onQuantityChangedForPosition(position);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            };

            etSoLuong.addTextChangedListener(textWatcher);
        }
    }
}

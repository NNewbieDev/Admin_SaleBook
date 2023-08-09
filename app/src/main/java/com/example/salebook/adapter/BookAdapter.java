package com.example.salebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.model.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;
    public void setData(List<Book> list){
        this.bookList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_product_manager, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(bookList != null){
            return bookList.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }

}

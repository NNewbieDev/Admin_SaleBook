package com.example.salebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manager_item, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        if(book == null){
            return;
        }
//        holder.bookCate.setText(book.getCategoriesId().getName());
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookPub.setText(book.getPublisher());
        holder.bookDimens.setText(book.getDimension());
        holder.bookPrice.setText(String.valueOf(book.getPrice()).toString());
    }

    @Override
    public int getItemCount() {
        if(bookList != null){
            return bookList.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookPub;
        private TextView bookDimens;
        private TextView bookPrice;
        private TextView bookCate;
        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookPub = itemView.findViewById(R.id.bookPub);
            bookDimens = itemView.findViewById(R.id.bookDimens);
            bookPrice = itemView.findViewById(R.id.bookPrice);
            bookCate = itemView.findViewById(R.id.bookCate);
        }

        @Override
        public void onClick(View view) {

        }
    }

}

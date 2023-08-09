package com.example.salebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> categoryList;
    public void setData(List<Category> list){
        this.categoryList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_category_manager, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

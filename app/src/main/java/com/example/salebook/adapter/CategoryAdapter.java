package com.example.salebook.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.model.Category;

import org.w3c.dom.Text;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_manager_item, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categoryList.get(position);
        if(categoryList == null){
            return;
        }
        holder.categoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        if(categoryList != null){
            return categoryList.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView totalBook;
        private TextView categoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            totalBook = itemView.findViewById(R.id.totalBook);
            categoryName = itemView.findViewById(R.id.categoryName);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

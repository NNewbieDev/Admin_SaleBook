package com.example.salebook.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
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
        DatabaseAdapter db = new DatabaseAdapter(holder.itemView.getContext());
        holder.categoryName.setText(category.getName());

        holder.modifyBtn.setOnClickListener(btn->{

        });

        holder.delBtn.setOnClickListener(btn->{
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(holder.itemView.getContext());

            View viewAlert = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.alert_delete, null);
            alertBuilder.setView(viewAlert);

            RelativeLayout closeBtn = viewAlert.findViewById(R.id.closeDialog);
            LinearLayout acceptBtn = viewAlert.findViewById(R.id.acceptBtn);
            TextView cancelBtn = viewAlert.findViewById(R.id.cancelBtn);

            AlertDialog alert = alertBuilder.create();
            closeBtn.setOnClickListener(close -> {
                alert.dismiss();
            });
            cancelBtn.setOnClickListener(cancel->{
                alert.dismiss();
            });
            acceptBtn.setOnClickListener(accept -> {
                db.deleteCateByName(category.getName());
                categoryList.remove(position);
                setData(categoryList);
                alert.dismiss();

            });
            alert.show();
        });
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
        private ConstraintLayout delBtn;
        private ConstraintLayout modifyBtn;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            totalBook = itemView.findViewById(R.id.totalBook);
            categoryName = itemView.findViewById(R.id.categoryName);
            delBtn = itemView.findViewById(R.id.deleteBtn);
            modifyBtn = itemView.findViewById(R.id.adjustBtn);
        }

        @Override
        public void onClick(View view) {

        }
    }
}

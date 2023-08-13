package com.example.salebook.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.adapter.CategoryAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Category;

import java.util.ArrayList;
import java.util.List;

public class FragCategoryManager extends Fragment {

    private ConstraintLayout btnAddCate;
    private RelativeLayout closeBtn;
    private EditText inputCateName;
    private LinearLayout addCate;
    private RecyclerView recyclerView;
    private List<Category> categoryList;
    private CategoryAdapter categoryAdapter;
    private DatabaseAdapter db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.f_category_manager, container, false);

        btnAddCate = layout.findViewById(R.id.btnAddCategory);

        recyclerView = layout.findViewById(R.id.listCategoryManager);
        db = new DatabaseAdapter(layout.getContext());
        categoryAdapter = new CategoryAdapter();

        categoryList = db.getAllCate();
        categoryAdapter.setData(categoryList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(categoryAdapter);

        btnAddCate.setOnClickListener(btn -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            View dialogView = getLayoutInflater().inflate(R.layout.form_category, null);
            builder.setView(dialogView);

            inputCateName = dialogView.findViewById(R.id.inputCateName);
            addCate = dialogView.findViewById(R.id.addCate);

            addCate.setOnClickListener(add -> {
                String cateName = inputCateName.getText().toString().trim();

                if (cateName.isEmpty()) {
                    Toast.makeText(layout.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.addCate(cateName)) {
                        Toast.makeText(layout.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(layout.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    categoryList.add(new Category(cateName));
                }
            });

            closeBtn = dialogView.findViewById(R.id.closeDialog);
            AlertDialog dialog = builder.create();
            closeBtn.setOnClickListener(close -> {
                dialog.dismiss();
            });

            dialog.show();
        });
        return layout;
    }
}

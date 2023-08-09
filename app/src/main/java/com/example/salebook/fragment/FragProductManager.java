package com.example.salebook.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.salebook.R;

public class FragProductManager extends Fragment {
    private ConstraintLayout btnAddBook;
    private RelativeLayout closeBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout =inflater.inflate(R.layout.f_product_manager, container, false);

        btnAddBook = layout.findViewById(R.id.btnAddBook);

        btnAddBook.setOnClickListener(btn -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View dialogView = getLayoutInflater().inflate(R.layout.form_create_book, null);
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();
            closeBtn = dialogView.findViewById(R.id.closeDialog);
            closeBtn.setOnClickListener(close -> {
                dialog.dismiss();
            });

            dialog.show();
        });

        return layout;
    }
}

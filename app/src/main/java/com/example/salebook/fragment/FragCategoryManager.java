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

public class FragCategoryManager extends Fragment {

    private ConstraintLayout btnAddCate;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.f_category_manager, container, false);

        btnAddCate = layout.findViewById(R.id.btnAddCategory);

        btnAddCate.setOnClickListener(btn -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

            View dialogView = getLayoutInflater().inflate(R.layout.form_category, null);
            builder.setView(dialogView);
            RelativeLayout closeBtn = dialogView.findViewById(R.id.closeDialog);

            AlertDialog dialog = builder.create();
            closeBtn.setOnClickListener(close -> {
                dialog.dismiss();
            });

            dialog.show();
        });
        return layout;
    }
}

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
import com.example.salebook.adapter.BookAdapter;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;
import com.example.salebook.model.Category;

import java.util.List;

public class FragProductManager extends Fragment {
    private ConstraintLayout btnAddBook;
    private RelativeLayout closeBtn;
    private RecyclerView recyclerView;
    private List<Book> bookList;
    private BookAdapter bookAdapter;
    private DatabaseAdapter db;
    private LinearLayout btnAdd;
    private EditText edTitle;
    private EditText edAuthor;
    private EditText edPub;
    private EditText edImg;
    private EditText edPrice;
    private EditText edPage;
    private EditText edDimens;
    private EditText edCate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.f_product_manager, container, false);

        btnAddBook = layout.findViewById(R.id.btnAddBook);
        recyclerView = layout.findViewById(R.id.listProductManager);

        bookAdapter = new BookAdapter();
        db = new DatabaseAdapter(layout.getContext());

        bookList = db.getAllBook();
        bookAdapter.setData(bookList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(layout.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(bookAdapter);

        btnAddBook.setOnClickListener(btn -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View dialogView = getLayoutInflater().inflate(R.layout.form_create_book, null);
            builder.setView(dialogView);

            btnAdd = dialogView.findViewById(R.id.addBookBtn);
            edTitle = dialogView.findViewById(R.id.inputTitle);
            edAuthor = dialogView.findViewById(R.id.inputAuthor);
            edPub = dialogView.findViewById(R.id.inputPub);
            edImg = dialogView.findViewById(R.id.inputImage);
            edPrice = dialogView.findViewById(R.id.inputPrice);
            edPage = dialogView.findViewById(R.id.inputPage);
            edDimens = dialogView.findViewById(R.id.inputDimension);
            edCate = dialogView.findViewById(R.id.inputCate);

            btnAdd.setOnClickListener(add -> {
                String title = edTitle.getText().toString().trim();
                String author = edAuthor.getText().toString().trim();
                String pub = edPub.getText().toString().trim();
                String image = edImg.getText().toString().trim();
                String dimens = edDimens.getText().toString().trim();

                Category category = new Category();
                category.setName(edCate.getText().toString().trim());
                String pages = edPage.getText().toString().trim();
                String price = edPrice.getText().toString().trim();

                if (title.isEmpty() && author.isEmpty() && pub.isEmpty() && image.isEmpty() && edPrice.getText().toString().trim().isEmpty()) {
                    Toast.makeText(layout.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.addBook(title, author, pub, Integer.parseInt(price), 1000, image, Integer.parseInt(pages), dimens, category)) {
                        Toast.makeText(layout.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(layout.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                    bookList.add(new Book(title, author, pub, Integer.parseInt(price), 1000, image, Integer.parseInt(pages), dimens, category));
                }

            });

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

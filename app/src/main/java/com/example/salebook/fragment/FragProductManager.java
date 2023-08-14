package com.example.salebook.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
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

            Spinner spinner = dialogView.findViewById(R.id.spinRole);
            List<Category> categoryList = db.getAllCate();
            ArrayAdapter adapter = new ArrayAdapter(dialogView.getContext(), android.R.layout.simple_spinner_item, categoryList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    btnAdd.setOnClickListener(add -> {
                        String title = edTitle.getText().toString().trim();
                        String author = edAuthor.getText().toString().trim();
                        String pub = edPub.getText().toString().trim();
                        String image = edImg.getText().toString().trim();
                        String price = edPrice.getText().toString().trim();

                        Category category = categoryList.get(i);

                        if (title.isEmpty() || author.isEmpty() || pub.isEmpty() || image.isEmpty() || price.isEmpty()) {
                            Toast.makeText(dialogView.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (db.addBook(title, author, pub, Integer.parseInt(price), 1000, image, category)) {
                                Toast.makeText(dialogView.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                                bookList.add(new Book(title, author, pub, Integer.parseInt(price), 1000, image, category));
                                bookAdapter.setData(bookList);
                            } else {
                                Toast.makeText(dialogView.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    btnAdd.setOnClickListener(add -> {
                        String title = edTitle.getText().toString().trim();
                        String author = edAuthor.getText().toString().trim();
                        String pub = edPub.getText().toString().trim();
                        String image = edImg.getText().toString().trim();
                        String price = edPrice.getText().toString().trim();

                        Category category = categoryList.get(0);

                        if (title.isEmpty() || author.isEmpty() || pub.isEmpty() || image.isEmpty() || price.isEmpty()) {
                            Toast.makeText(dialogView.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            if (db.addBook(title, author, pub, Integer.parseInt(price), 1000, image, category)) {
                                Toast.makeText(dialogView.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                                bookList.add(new Book(title, author, pub, Integer.parseInt(price), 1000, image, category));
                                bookAdapter.setData(bookList);
                            } else {
                                Toast.makeText(dialogView.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
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

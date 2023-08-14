package com.example.salebook.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.Book;
import com.example.salebook.model.Category;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> bookList;

    public void setData(List<Book> list) {
        this.bookList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_manager_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        if (book == null) {
            return;
        }
        holder.bookCate.setText(book.getCategoriesId().getName());
        holder.bookTitle.setText(book.getTitle());
        holder.bookAuthor.setText(book.getAuthor());
        holder.bookPub.setText(book.getPublisher());
        holder.bookPrice.setText(String.valueOf(book.getPrice()).toString());

        DatabaseAdapter db = new DatabaseAdapter(holder.itemView.getContext());
//        DELETE
        holder.btnDel.setOnClickListener(btn -> {
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
            cancelBtn.setOnClickListener(cancel -> {
                alert.dismiss();
            });
            acceptBtn.setOnClickListener(accept -> {
                db.deleteBookByName(book.getTitle());
                bookList.remove(position);
                setData(bookList);
                alert.dismiss();
            });
            alert.show();
        });
//        UPDATE
//        holder.adjustBtn.setOnClickListener(btn -> {
//            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(holder.itemView.getContext());
//
//            View viewAlert = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.form_modify_book, null);
//            alertBuilder.setView(viewAlert);
//            AlertDialog alert = alertBuilder.create();
//
//            LinearLayout applyBtn = viewAlert.findViewById(R.id.applyChangeBtn);
//            TextView cancelBtn = viewAlert.findViewById(R.id.cancelBtn);
//            RelativeLayout closeBtn = viewAlert.findViewById(R.id.closeDialog);
//
//            EditText edTitle = viewAlert.findViewById(R.id.inputTitle);
//            EditText edAuthor = viewAlert.findViewById(R.id.inputAuthor);
//            EditText edPub = viewAlert.findViewById(R.id.inputPub);
//            EditText edImg = viewAlert.findViewById(R.id.inputImage);
//            EditText edPrice = viewAlert.findViewById(R.id.inputPrice);
//            EditText edDesc = viewAlert.findViewById(R.id.inputDesc);
//            EditText edDimens = viewAlert.findViewById(R.id.inputDimension);
//            EditText edPage = viewAlert.findViewById(R.id.inputPage);
//            EditText edQuantity = viewAlert.findViewById(R.id.inputQuantity);
//
//            String title = edTitle.getText().toString().trim();
//            String author = edAuthor.getText().toString().trim();
//            String pub = edPub.getText().toString().trim();
//            String image = edImg.getText().toString().trim();
//            String dimens = edDimens.getText().toString().trim();
//            String desc = edDesc.getText().toString().trim();
//
//            int quantity, pages, price;
//            try {
//                pages = Integer.parseInt(edPage.getText().toString().trim());
//                price = Integer.parseInt(edPrice.getText().toString().trim());
//                quantity = Integer.parseInt(edQuantity.getText().toString().trim());
//            } catch (NumberFormatException e) {
//                quantity = 0;
//                pages = 0;
//                quantity = 0;
//            }
//            edTitle.setText(book.getTitle());
//            edAuthor.setText(book.getAuthor());
//            edPub.setText(book.getPublisher());
//            edImg.setText(book.getImage());
//            edQuantity.setText(String.valueOf(book.getQuantity()).toString());
//
//
//            Spinner spinner = viewAlert.findViewById(R.id.spinRole);
//            List<Category> categoryList = db.getAllCate();
//            ArrayAdapter adapter = new ArrayAdapter(viewAlert.getContext(), android.R.layout.simple_spinner_item, categoryList);
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//            spinner.setAdapter(adapter);
//
//            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                    applyBtn.setOnClickListener(add -> {
//                        String title = edTitle.getText().toString().trim();
//                        String author = edAuthor.getText().toString().trim();
//                        String pub = edPub.getText().toString().trim();
//                        String image = edImg.getText().toString().trim();
//                        String dimens = edDimens.getText().toString().trim();
//                        String pages = edPage.getText().toString().trim();
//                        String price = edPrice.getText().toString().trim();
//                        String desc = edDesc.getText().toString().trim();
//                        String quantity = edQuantity.getText().toString().trim();
//
//                        if (title.isEmpty() && author.isEmpty() && pub.isEmpty() && edPrice.getText().toString().trim().isEmpty()) {
//                            Toast.makeText(viewAlert.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Category selectedCategory = categoryList.get(i); // Get the selected category from the list
//
//                            if (db.updateBookInfo(book.getTitle(), title, author, pub, image, dimens, Integer.parseInt(price), Integer.parseInt(pages), desc, Integer.parseInt(quantity), selectedCategory)) {
//                                Toast.makeText(viewAlert.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
//                                book.setTitle(title);
//                                book.setAuthor(author);
//                                book.setPublisher(pub);
//                                book.setPrice(Integer.parseInt(price));
//                                notifyDataSetChanged();
//                                alert.dismiss();
//                            } else {
//                                Toast.makeText(viewAlert.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> adapterView) {
//
//                }
//            });
//
//            cancelBtn.setOnClickListener(cancel -> {
//                alert.dismiss();
//            });
//
//            closeBtn.setOnClickListener(close -> {
//                alert.dismiss();
//            });
//
//            alert.show();
//        });
//    }

        holder.adjustBtn.setOnClickListener(btn -> {
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(holder.itemView.getContext());

            View viewAlert = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.form_modify_book, null);
            alertBuilder.setView(viewAlert);
            AlertDialog alert = alertBuilder.create();

            LinearLayout applyBtn = viewAlert.findViewById(R.id.applyChangeBtn);
            TextView cancelBtn = viewAlert.findViewById(R.id.cancelBtn);
            RelativeLayout closeBtn = viewAlert.findViewById(R.id.closeDialog);

            EditText edTitle = viewAlert.findViewById(R.id.inputTitle);
            EditText edAuthor = viewAlert.findViewById(R.id.inputAuthor);
            EditText edPub = viewAlert.findViewById(R.id.inputPub);
            EditText edImg = viewAlert.findViewById(R.id.inputImage);
            EditText edPrice = viewAlert.findViewById(R.id.inputPrice);
            EditText edDesc = viewAlert.findViewById(R.id.inputDesc);
            EditText edDimens = viewAlert.findViewById(R.id.inputDimension);
            EditText edPage = viewAlert.findViewById(R.id.inputPage);
            EditText edQuantity = viewAlert.findViewById(R.id.inputQuantity);

            Spinner spinner = viewAlert.findViewById(R.id.spinRole);
            List<Category> categoryList = db.getAllCate();
            ArrayAdapter<Category> adapter = new ArrayAdapter<>(viewAlert.getContext(), android.R.layout.simple_spinner_item, categoryList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            // Set the existing values from the 'book' object to the EditText fields
            edTitle.setText(book.getTitle());
            edAuthor.setText(book.getAuthor());
            edPub.setText(book.getPublisher());
            edImg.setText(book.getImage());
            edPrice.setText(String.valueOf(book.getPrice()));
            edDimens.setText(book.getDimension());
            edPage.setText(String.valueOf(book.getPages()));
            edQuantity.setText(String.valueOf(book.getQuantity()));

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    applyBtn.setOnClickListener(add -> {
                        String newTitle = edTitle.getText().toString().trim();
                        String newAuthor = edAuthor.getText().toString().trim();
                        String newPub = edPub.getText().toString().trim();
                        String newImage = edImg.getText().toString().trim();
                        String newDimens = edDimens.getText().toString().trim();
                        String newPages = edPage.getText().toString().trim();
                        String newPrice = edPrice.getText().toString().trim();
                        String newDesc = edDesc.getText().toString().trim();
                        String newQuantity = edQuantity.getText().toString().trim();

                        if (newTitle.isEmpty() || newAuthor.isEmpty() || newPub.isEmpty() || newPrice.isEmpty()) {
                            Toast.makeText(viewAlert.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            Category selectedCategory = categoryList.get(i); // Get the selected category from the list

                            if (db.updateBookInfo(book.getTitle(), newTitle, newAuthor, newPub, newImage, newDimens, Integer.parseInt(newPrice), Integer.parseInt(newPages), newDesc, Integer.parseInt(newQuantity), selectedCategory)) {
                                Toast.makeText(viewAlert.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                                book.setTitle(newTitle);
                                book.setAuthor(newAuthor);
                                book.setPublisher(newPub);
                                book.setPrice(Integer.parseInt(newPrice));
                                notifyDataSetChanged();
                                alert.dismiss();
                            } else {
                                Toast.makeText(viewAlert.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    applyBtn.setOnClickListener(add -> {
                        String newTitle = edTitle.getText().toString().trim();
                        String newAuthor = edAuthor.getText().toString().trim();
                        String newPub = edPub.getText().toString().trim();
                        String newImage = edImg.getText().toString().trim();
                        String newDimens = edDimens.getText().toString().trim();
                        String newPages = edPage.getText().toString().trim();
                        String newPrice = edPrice.getText().toString().trim();
                        String newDesc = edDesc.getText().toString().trim();
                        String newQuantity = edQuantity.getText().toString().trim();

                        if (newTitle.isEmpty() || newAuthor.isEmpty() || newPub.isEmpty() || newPrice.isEmpty()) {
                            Toast.makeText(viewAlert.getContext(), "Điền thiếu thông tin", Toast.LENGTH_SHORT).show();
                        } else {
                            Category selectedCategory = categoryList.get(0); // Get the selected category from the list

                            if (db.updateBookInfo(book.getTitle(), newTitle, newAuthor, newPub, newImage, newDimens, Integer.parseInt(newPrice), Integer.parseInt(newPages), newDesc, Integer.parseInt(newQuantity), selectedCategory)) {
                                Toast.makeText(viewAlert.getContext(), "Đã thêm", Toast.LENGTH_SHORT).show();
                                book.setTitle(newTitle);
                                book.setAuthor(newAuthor);
                                book.setPublisher(newPub);
                                book.setPrice(Integer.parseInt(newPrice));
                                notifyDataSetChanged();
                                alert.dismiss();
                            } else {
                                Toast.makeText(viewAlert.getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            cancelBtn.setOnClickListener(cancel -> {
                alert.dismiss();
            });

            closeBtn.setOnClickListener(close -> {
                alert.dismiss();
            });

            alert.show();
        });
    }
        @Override
    public int getItemCount() {
        if (bookList != null) {
            return bookList.size();
        }
        return 0;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView bookTitle;
        private TextView bookAuthor;
        private TextView bookPub;
        private TextView bookPrice;
        private TextView bookCate;
        private ConstraintLayout btnDel;
        private ConstraintLayout adjustBtn;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.bookTitle);
            bookAuthor = itemView.findViewById(R.id.bookAuthor);
            bookPub = itemView.findViewById(R.id.bookPub);
            bookPrice = itemView.findViewById(R.id.bookPrice);
            bookCate = itemView.findViewById(R.id.bookCate);
            btnDel = itemView.findViewById(R.id.deleteBtn);
            adjustBtn = itemView.findViewById(R.id.adjustBtn);
        }

        @Override
        public void onClick(View view) {

        }
    }

}

package com.example.salebook.adapter;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;

import com.example.salebook.model.User;

import java.util.ArrayList;
import java.util.List;

// cách làm không đổi
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;

    public void setData(List<User> list) {
        this.userList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_home_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);
        if (userList == null) {
            return;
        }
        holder.labelUsername.setText(user.getUsername());
        holder.labelPassword.setText("*****");
        holder.viewAccount.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            View dialogView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.form_sign_in, null);
            builder.setView(dialogView);

            Button signInBtn = dialogView.findViewById(R.id.signInBtn);
            EditText inputUsername = dialogView.findViewById(R.id.inputUsername);
            inputUsername.setText(user.getUsername());
            EditText inputPassword = dialogView.findViewById(R.id.inputPassword);

            // Create and show the dialog
            AlertDialog dialog = builder.create();
            signInBtn.setOnClickListener(btn -> {
                DatabaseAdapter db = new DatabaseAdapter(holder.itemView.getContext());
                String password = inputPassword.getText().toString().trim();
                User checkUser = db.getUserByUsernameAndPassword(user.getUsername(), password);
                if(checkUser == null)
                {
                    Toast.makeText(dialogView.getContext(), "Tài khoản không tồn tại" , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(dialogView.getContext(), checkUser.getRoleId().getRoleName(), Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });
            dialog.show();
        });
    }


    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView labelUsername;
        private TextView labelPassword;
        private LinearLayout viewAccount;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            labelUsername = itemView.findViewById(R.id.labelUsername);
            labelPassword = itemView.findViewById(R.id.labelPassword);
            viewAccount = itemView.findViewById(R.id.accountUser);
        }

        @Override
        public void onClick(View view) {

        }

    }
}

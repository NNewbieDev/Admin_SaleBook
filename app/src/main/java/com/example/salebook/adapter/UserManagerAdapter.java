package com.example.salebook.adapter;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.salebook.R;
import com.example.salebook.database.DatabaseAdapter;
import com.example.salebook.model.User;

import java.util.List;

public class UserManagerAdapter extends RecyclerView.Adapter<UserManagerAdapter.UserManagerVH> {

    private List<User> userList;
    private DatabaseAdapter db;

    public void setData(List<User> list) {
        this.userList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserManagerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.f_home_item, parent, false);
        return new UserManagerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserManagerVH holder, int position) {
        User user = userList.get(position);
        if (userList == null) {
            return;
        }
        holder.labelUsername.setText(user.getUsername());
        holder.labelPassword.setText("*****");
        holder.viewAccount.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext());
            View dialogView = LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.form_modify_account, null);
            builder.setView(dialogView);

            AlertDialog dialog = builder.create();

            TextView btnDel = dialogView.findViewById(R.id.delAccountBtn);
            db = new DatabaseAdapter(holder.itemView.getContext());
            btnDel.setOnClickListener(btn -> {
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
                    db.deleteUserByUsername(user.getUsername());
                    userList.remove(position);
                    setData(userList);
                    alert.dismiss();
                    dialog.dismiss();
                });
                alert.show();
            });

            LinearLayout applyBtn = dialogView.findViewById(R.id.applyChangeBtn);
            String arrayRole[] = {"Admin", "User"};
//            ArrayAdapter<String> roleAdapter = ArrayAdapter.createFromResource(dialogView.getContext(), ,);
            Spinner spinRole = dialogView.findViewById(R.id.spinRole);
            EditText ipUsername = dialogView.findViewById(R.id.inputUsername);
            ipUsername.setText(user.getUsername());

            applyBtn.setOnClickListener(btn -> {
                String username = ipUsername.getText().toString().trim();

//                if( db.updateUserInfo(username,)){
//                    Toast.makeText(holder.itemView.getContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
//                }else {
//                    Toast.makeText(holder.itemView.getContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
//                }
            });

            dialog.show();

            RelativeLayout closeBtn = dialogView.findViewById(R.id.closeDialog);
            closeBtn.setOnClickListener(btn -> {
                dialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class UserManagerVH extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView labelUsername;
        private TextView labelPassword;
        private LinearLayout viewAccount;

        public UserManagerVH(@NonNull View itemView) {
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

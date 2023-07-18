package com.example.salebook;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class OnClickHelper implements View.OnClickListener {
    private Context context;

    public OnClickHelper(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, CartActivity.class);
        context.startActivity(intent);
    }
}

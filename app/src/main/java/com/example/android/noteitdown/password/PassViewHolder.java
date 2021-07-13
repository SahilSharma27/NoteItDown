package com.example.android.noteitdown.password;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;

public class PassViewHolder extends RecyclerView.ViewHolder {
    View itemView;
    TextView titleview;

    public PassViewHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        this.titleview = itemView.findViewById(R.id.pass_item_title);



    }
}

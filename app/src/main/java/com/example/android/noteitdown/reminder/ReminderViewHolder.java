package com.example.android.noteitdown.reminder;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;


public class ReminderViewHolder extends RecyclerView.ViewHolder {
    TextView t1,t2;
    View itemView;
    public ReminderViewHolder(@NonNull  View itemView) {
        super(itemView);
        this.itemView = itemView;
        t1 = itemView.findViewById(R.id.rem_title);
        t2 = itemView.findViewById(R.id.rem_description);
    }
}

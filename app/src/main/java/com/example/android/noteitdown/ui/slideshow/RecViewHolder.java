package com.example.android.noteitdown.ui.slideshow;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;
//;import org.jetbrains.annotations.NotNull;

public class RecViewHolder extends RecyclerView.ViewHolder {

    TextView recName;
    LinearLayout container;
    public RecViewHolder(@NonNull View itemView) {
        super(itemView);
        recName = itemView.findViewById(R.id.rec_name);
        container = itemView.findViewById(R.id.rec_container);


    }
}

package com.example.android.noteitdown.password;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;

import java.util.List;

public class PassAdapater extends RecyclerView.Adapter<PassViewHolder> {
    Context context;
    OnPassClickListener listener;
    List <PassWord> allpasses;

    public PassAdapater(Context context, List<PassWord> allpasses, OnPassClickListener listener){
        this.context=context;
        this.listener=listener;
        this.allpasses=allpasses;
    }

    @NonNull
    @Override
    public PassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.pass_layout, parent, false);
        return new PassViewHolder(rowlayout);
    }

    @Override
    public void onBindViewHolder(@NonNull  PassViewHolder holder, int position) {

        PassWord pass = allpasses.get(position);
        holder.titleview.setText(pass.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPassItemClick(view,holder.getAbsoluteAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return allpasses.size();
    }
}

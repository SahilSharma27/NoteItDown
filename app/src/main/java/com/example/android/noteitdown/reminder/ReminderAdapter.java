package com.example.android.noteitdown.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;
import com.example.android.noteitdown.password.PassViewHolder;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderViewHolder> {

    Context context;
    List<Reminder> allRems;
    OnRemClickListener listener;

    public ReminderAdapter(Context context, List<Reminder> allRems ,OnRemClickListener listener) {
        this.allRems = allRems;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowlayout = inflater.inflate(R.layout.rem_item_layout, parent, false);
        return new ReminderViewHolder(rowlayout);

    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {

        Reminder rem = allRems.get(position);

        holder.t1.setText(rem.getRemtitle());
        holder.t2.setText(rem.getRemDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRemItemClick(view,holder.getAbsoluteAdapterPosition());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onRemItemLongClick(view,holder.getAbsoluteAdapterPosition());
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return allRems.size();
    }
}

package com.example.android.noteitdown.reminder;

import android.view.View;

public interface OnRemClickListener {
    public void onRemItemClick(View view,int position);
    public void onRemItemLongClick(View view ,int position);

}

package com.example.android.noteitdown.ui.home;

import android.view.View;

public interface OnNoteClickListener {
    void onNoteItemClick(View view,int position);
    void onNoteItemLongClick(View view ,int position);
}

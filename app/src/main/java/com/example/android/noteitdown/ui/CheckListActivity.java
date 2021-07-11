package com.example.android.noteitdown.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.noteitdown.R;

import java.util.ArrayList;

public class CheckListActivity extends AppCompatActivity {
    TextView Title;
    Button savebtn;
    RecyclerView taskRCV;
    Integer taskCount;
    Spinner spinner;
    ClAdapter clAdapter;

    ArrayList<LinearLayout> taskLayouts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_list);

        spinner =  findViewById(R.id.taskcount);

        Integer[] items = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        spinner.setAdapter(adapter);

        taskCount = (Integer) spinner.getSelectedItem();

//        taskLayouts = new ArrayList<>();
//
//        for( int i = 0;i<taskCount;i++){
//
//
//        }




    }
}
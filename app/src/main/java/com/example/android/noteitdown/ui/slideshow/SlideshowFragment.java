package com.example.android.noteitdown.ui.slideshow;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.noteitdown.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment implements OnRecClickListener{

//    private SlideshowViewModel slideshowViewModel;

    RecyclerView recyclerView ;
    RecAdapter adapter;
//    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath())
File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/NoteItDown");
    View root;
    private List<File> fileList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);
        root  = inflater.inflate(R.layout.fragment_slideshow, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });


        displayfiles();




        return root;
    }

    private void displayfiles() {

        recyclerView = root.findViewById(R.id.recRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        fileList = new ArrayList<>();

        fileList.addAll(findfile(path));

        adapter = new RecAdapter(getContext(), fileList,this);
        recyclerView.setAdapter(adapter);
    }

    public ArrayList<File> findfile(File file){
        ArrayList<File > arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for(File singleFile : files){
            if(singleFile.getName().toLowerCase().endsWith(".3gp")){
                arrayList.add(singleFile);
            }
        }

        return arrayList;
    }

    @Override
    public void OnSlected(File file) {
        Uri uri = FileProvider.getUriForFile(getContext(),getContext().getApplicationContext().getPackageName()+".provider",file);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri,"audio/x-wav");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        getContext().startActivity(intent);

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            displayfiles();
        }
    }
}
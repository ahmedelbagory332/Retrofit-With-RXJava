package com.example.bego.retrofitmvvmrecyclerview.ui.main;


import android.arch.lifecycle.Observer;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import com.example.bego.retrofitmvvmrecyclerview.R;
import com.example.bego.retrofitmvvmrecyclerview.pojo.PostModel;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    PostViewModel postViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        postViewModel.getPosts();
        RecyclerView recyclerView = findViewById(R.id.recycler);
        final PostAdapter adapter = new PostAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        postViewModel.postsMutableLiveData.observe(this, new Observer<List<PostModel>>() {
            @Override
            public void onChanged(List<PostModel> postModels) {
                adapter.setList(postModels);
            }
        });


    }
}

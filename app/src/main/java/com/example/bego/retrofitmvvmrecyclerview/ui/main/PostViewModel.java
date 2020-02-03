package com.example.bego.retrofitmvvmrecyclerview.ui.main;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.bego.retrofitmvvmrecyclerview.data.PostsClient;
import com.example.bego.retrofitmvvmrecyclerview.pojo.PostModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PostViewModel extends ViewModel {

    MutableLiveData<List<PostModel>> postsMutableLiveData = new MutableLiveData<>();

    private static final String TAG = "PostViewModel";
    @SuppressLint("CheckResult")
    public void getPosts() {
        Single<List<PostModel>> observable   = PostsClient.getINSTANCE().getPosts()
                                  .subscribeOn(Schedulers.io()) // to switch from main thread to i/o thread to get data / main thread cant wait tell data is come
                                  .observeOn(AndroidSchedulers.mainThread()); // to switch from i/o thread to main thread to show data

       observable.subscribe(o ->postsMutableLiveData.setValue(o),e -> Log.d(TAG, "getError: "+e));

    }

}

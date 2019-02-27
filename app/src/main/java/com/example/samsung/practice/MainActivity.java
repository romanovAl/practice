package com.example.samsung.practice;

import android.util.Log;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.json.JSONException;


public class MainActivity extends AppCompatActivity {

    private Button button;
    private RecyclerView recyclerView;
    static String url = "https://jsonplaceholder.typicode.com/comments";
    private JSONPlaceHolderApi jsonPlaceHolderApi;
    private androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        recyclerView = findViewById(R.id.commentsRecycler);


        try {
            initObs();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void initObs() throws IOException, JSONException {
        jsonPlaceHolderApi = new JSONPlaceHolderApi(url);
        jsonPlaceHolderApi.getUserList(button)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ArrayList<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(MainActivity.this.toString(), "onSubscribe: " + d.toString());
                    }

                    @Override
                    public void onSuccess(ArrayList<Comment> comments) {

                        button.setVisibility(View.INVISIBLE);
                        recyclerView.setLayoutManager(new GridLayoutManager
                                (MainActivity.this, 1));
                        recyclerView.setAdapter(new CommentsRecycleAdapter(comments));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
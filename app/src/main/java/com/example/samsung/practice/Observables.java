package com.example.samsung.practice;

import android.view.View;
import android.widget.Button;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class Observables {

    static String url = "https://jsonplaceholder.typicode.com/comments";

    public static Single<ArrayList<Comment>> getSingle(final Button button) {
        return Single.create(new SingleOnSubscribe<ArrayList<Comment>>() {
            @Override
            public void subscribe(final SingleEmitter<ArrayList<Comment>> emitter) throws Exception {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JSONPlaceHolderApi jsonPlaceHolderApi = new JSONPlaceHolderApi(url);

                    }
                });
            }
        });
    }

}

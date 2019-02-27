package com.example.samsung.practice;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;

public class JSONPlaceHolderApi {
    private String urlPath;
    int id = 0;
    int postID = 0;
    String name;
    String email;
    String body;

    public JSONPlaceHolderApi(String urlPath) {
        this.urlPath = urlPath;
    }

    public Single<ArrayList<Comment>> getUserList(final Button button) throws IOException, JSONException {
        return Single.create(new SingleOnSubscribe<ArrayList<Comment>>() {
            @Override
            public void subscribe(final SingleEmitter<ArrayList<Comment>> emitter) throws Exception {
                button.setOnClickListener(new View.OnClickListener() {


                    String userJsonStroke = getJsonFromServer(urlPath, 1000);
                    final JSONArray usersRoot = new JSONArray(userJsonStroke);

                    final ArrayList<Comment> comments = new ArrayList<>();

                    @Override
                    public void onClick (View v) {
                        for (int i = 0; i < usersRoot.length(); i++) {

                            Log.d(JSONPlaceHolderApi.this.toString(), "onClick: " + "click kek");

                            JSONObject userRoot ;

                            try {
                                userRoot = usersRoot.getJSONObject(i);
                                postID = userRoot.getInt("postId");
                                id = userRoot.getInt("id");
                                name = userRoot.getString("name");
                                email = userRoot.getString("email");
                                body = userRoot.getString("body");

                                Comment comment = new Comment(postID,id,name,email,body);
                                comments.add(comment);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }



                        }
                        emitter.onSuccess(comments);
                    }
                });
            }
        });







    }

    private String getJsonFromServer(String urlPath, int timeout) throws IOException {
        URL url = new URL(urlPath);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(timeout);
        connection.setReadTimeout(timeout);
        connection.connect();

        int serverResponseCode = connection.getResponseCode();
        switch (serverResponseCode) {
            case 200:
            case 201:
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String tmpLine;
                while ((tmpLine = br.readLine()) != null) {
                    sb.append(tmpLine).append("\n");
                }
                br.close();
                return sb.toString();
        }
        return null;
    }
}
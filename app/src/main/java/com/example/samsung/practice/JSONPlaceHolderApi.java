package com.example.samsung.practice;

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
    static String url = "https://jsonplaceholder.typicode.com/comments";
    int id = 0;
    String name;
    String email;
    String body;

    public JSONPlaceHolderApi(String urlPath) {
        this.urlPath = urlPath;
    }

    public Single<ArrayList<Comment>> getUserList(final Button button) throws IOException, JSONException {
        String userJsonStroke = getJsonFromServer(urlPath, 1000);
        final JSONArray usersRoot = new JSONArray(userJsonStroke);

        final ArrayList<Comment> comments = new ArrayList<>();
        return Single.create(new SingleOnSubscribe<ArrayList<Comment>>() {
            @Override
            public void subscribe(final SingleEmitter<ArrayList<Comment>> emitter) throws Exception {
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        JSONPlaceHolderApi jsonPlaceHolderApi = new JSONPlaceHolderApi(url);
                        for (int i = 0; i < usersRoot.length(); i++) {

                            JSONObject userRoot = null;
                            try {
                                userRoot = usersRoot.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            int postID = 0;
                            try {
                                postID = userRoot.getInt("postId");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                id = userRoot.getInt("id");
                                name = userRoot.getString("name");
                                email = userRoot.getString("email");
                                body = userRoot.getString("body");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                            Comment comment = new Comment(postID,id,name,email,body);
                            comments.add(comment);
                        }
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
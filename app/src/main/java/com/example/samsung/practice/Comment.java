package com.example.samsung.practice;

public class Comment {
    int postID;
    int id;
    String name;
    String email;
    String body;

    public Comment(int postID, int id, String name, String email, String body) {
        this.postID = postID;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

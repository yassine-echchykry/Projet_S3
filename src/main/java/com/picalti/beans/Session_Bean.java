package com.picalti.beans;

public class Session_Bean {
    private int id;
    private String token;
    private int user_id;

    public int getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public int getUserId() {
        return user_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }
}

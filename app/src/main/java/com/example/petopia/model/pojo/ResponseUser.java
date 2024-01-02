package com.example.petopia.model.pojo;

public class ResponseUser {
    private String message;

    private String unique_id;

    public ResponseUser(String message, String unique_id) {
        this.message = message;
        this.unique_id = unique_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser_id() {
        return unique_id;
    }

    public void setUser_id(String user_id) {
        this.unique_id = user_id;
    }
}

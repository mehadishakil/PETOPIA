package com.example.petopia.model.pojo;

import android.text.TextUtils;
import android.util.Patterns;

public class User {

    private String email, password;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer isValid() {
        if (TextUtils.isEmpty(getEmail())){
            return 0;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()){
            return 1;
        }else if (TextUtils.isEmpty(getPassword())){
            return 2;
        }else if (getPassword().length()<8){
            return 3;
        }else {
            return -1;
        }
    }
}

package com.example.petopia.view;

public interface ILogin {
    void onLoginSuccess(String message, String userID);
    void onLoginError(String message);
}

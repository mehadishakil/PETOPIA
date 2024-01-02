package com.example.petopia.view;

public interface ILogView {
    void onLoginSuccess(String message, String userID);
    void onLoginError(String message);
}

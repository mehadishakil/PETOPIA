package com.example.petopia.controller;

public interface ILogController {
    void onLogin(String email, String password);

    void onSignup(String email, String pass1, String pass2);
}

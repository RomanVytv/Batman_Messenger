package com.romanvytv.webapiandroidtest.presentation.login;


public interface LoginView {


    void showProgress();

    void hideProgress();

    void setEmailError();

    void setPasswordError();

    void navigateToHome(String token);

    void navigateToRegister();

    void showError(String text);
}

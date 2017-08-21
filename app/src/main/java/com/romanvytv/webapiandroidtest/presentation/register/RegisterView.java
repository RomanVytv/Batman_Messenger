package com.romanvytv.webapiandroidtest.presentation.register;

/**
 * Created by Роман on 15.08.2017.
 */

public interface RegisterView {

    void register();

    void showProgress();

    void hideProgress();

    void navigateToLogin();

    void setEmailError();

    void setPasswordError();

    void setConfirmPasswordError();

    void setNameError();

    void setSurnameError();

    void setPhoneNumberError();

    void setBirthDateError();

    void showError(String text);
}

package com.romanvytv.webapiandroidtest.presentation.register;

import com.romanvytv.webapiandroidtest.models.RegisterModel;


public interface RegisterInteraptor {

    interface OnRegisterFinishedListener {

        void onEmailError();

        void onPasswordError();

        void onConfirmPasswordError();

        void onNameError();

        void onSurnameError();

        void onPhoneNumberError();

        void onBirthDateError();

        void onSuccess();

        void showError(String text);
    }

    void register(RegisterModel model, RegisterInteraptor.OnRegisterFinishedListener listener);

}

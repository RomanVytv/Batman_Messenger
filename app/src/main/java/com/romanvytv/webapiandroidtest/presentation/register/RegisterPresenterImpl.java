package com.romanvytv.webapiandroidtest.presentation.register;


import com.romanvytv.webapiandroidtest.models.RegisterModel;

public class RegisterPresenterImpl implements RegisterPresenter, RegisterInteraptor.OnRegisterFinishedListener {

    private RegisterView registerView;
    private RegisterInteraptor registerInteractor;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        registerInteractor = new RegisterInteractorImpl();
    }

    @Override
    public void onEmailError() {
        if (registerView != null) {
            registerView.setEmailError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {
        if (registerView != null) {
            registerView.setPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onConfirmPasswordError() {
        if (registerView != null) {
            registerView.setConfirmPasswordError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onNameError() {
        if (registerView != null) {
            registerView.setNameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSurnameError() {
        if (registerView != null) {
            registerView.setSurnameError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onPhoneNumberError() {
        if (registerView != null) {
            registerView.setPhoneNumberError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onBirthDateError() {
        if (registerView != null) {
            registerView.setBirthDateError();
            registerView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (registerView != null) {
            registerView.navigateToLogin();
            registerView.hideProgress();
        }
    }

    @Override
    public void showError(String text) {
        if (registerView != null) {
            registerView.showError(text);
        }
    }

    @Override
    public void validateData(RegisterModel registerModel) {
        if(registerView != null){
            registerView.showProgress();
        }
        registerInteractor.register(registerModel,this);
    }

    @Override
    public void onDestroy() {
        registerView = null;
    }


}

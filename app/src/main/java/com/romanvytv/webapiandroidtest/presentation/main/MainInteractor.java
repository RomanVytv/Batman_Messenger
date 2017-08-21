package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.UserViewModel;

public interface MainInteractor {

    interface OnLoadingFinishedListener {

        void onSuccess(UserViewModel user);

        void showError(String text);
    }

    void fillUserData(String token, final MainInteractor.OnLoadingFinishedListener listener);
}

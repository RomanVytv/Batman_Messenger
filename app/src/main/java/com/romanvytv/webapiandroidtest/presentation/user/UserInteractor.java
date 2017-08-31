package com.romanvytv.webapiandroidtest.presentation.user;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

/**
 * Created by Роман on 29.08.2017.
 */

public interface UserInteractor {

    void loadUser(String token, String userId, final UserInteractor.OnLoadingFinishedListener listener);

    interface OnLoadingFinishedListener {

        void onSuccess(UserViewModel user);

        void showError(String text);
    }

}

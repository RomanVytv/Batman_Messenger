package com.romanvytv.webapiandroidtest.presentation.friends;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 24.08.2017.
 */

public interface FriendsInteractor {

    interface OnLoadingFinishedListener {

        void onSuccess(List<UserViewModel> friends);

        void showError(String text);
    }

    void loadFriends(String token,final FriendsInteractor.OnLoadingFinishedListener listener);
}

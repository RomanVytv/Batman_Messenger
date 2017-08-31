package com.romanvytv.webapiandroidtest.presentation.search;

import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 28.08.2017.
 */

public interface SearchInterceptor {

    interface OnLoadingFinishedListener {

        void onSuccessSearch(List<UserViewModel> users);

        void onSuccessAddFriend();

        void showError(String text);
    }

    void findByEmail(String token, String query,final SearchInterceptor.OnLoadingFinishedListener listener);

    void findByName(String token, String query,final SearchInterceptor.OnLoadingFinishedListener listener);

    void findBySurname(String token, String query,final SearchInterceptor.OnLoadingFinishedListener listener);

    void findByPhone(String token, String query,final SearchInterceptor.OnLoadingFinishedListener listener);

    void addFriend(String token, String chatName,String friendId, final SearchInterceptor.OnLoadingFinishedListener listener);

}

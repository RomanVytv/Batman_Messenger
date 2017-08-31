package com.romanvytv.webapiandroidtest.presentation.search;

/**
 * Created by Роман on 28.08.2017.
 */

public interface SearchPresenter {


    void find(String token, String type, String value);

    void addFriend(String token,String chatName, String friendId);
}

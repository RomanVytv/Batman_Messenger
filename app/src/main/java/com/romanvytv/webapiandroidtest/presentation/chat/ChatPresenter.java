package com.romanvytv.webapiandroidtest.presentation.chat;

/**
 * Created by Роман on 21.08.2017.
 */

public interface ChatPresenter {

    void loadChatById(String token, int chatId);

    void loadChatByFriendId(String token, String friendId);

    void loadMembers(String token,int chatId);

    void loadCurrentUser(String token);

    void openChatById(String authToken, int id);

    void sendMessage(String authToken, int chatId, String message);
}


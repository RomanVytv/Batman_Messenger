package com.romanvytv.webapiandroidtest.presentation.chat;

import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.MessageViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

/**
 * Created by Роман on 21.08.2017.
 */

public class ChatPresenterImpl implements ChatPresenter, ChatInteractor.OnLoadingFinishedListener {

    private ChatView chatView;
    private ChatInteractor chatInteractor;

    public ChatPresenterImpl(ChatView chatView) {
        this.chatView = chatView;
        chatInteractor = new ChatInteractorImpl();
    }

    @Override
    public void loadChatById(String token, int chatId) {
        if(chatView != null){
            chatView.showProgress();
        }
        chatInteractor.loadChatById(token,chatId,this);
    }

    @Override
    public void loadChatByFriendId(String token, String  friendId) {
        if(chatView != null){
            chatView.showProgress();
        }
        chatInteractor.loadChatByFriendId(token,friendId,this);
    }

    @Override
    public void openChatById(String token, int id) {
        chatInteractor.openChatById(token,id,this);
    }

    @Override
    public void sendMessage(String authToken, int chatId, String message) {
        chatInteractor.sendMessage(authToken,chatId,message,this);
    }

    @Override
    public void loadMembers(String token, int chatId) {
        chatInteractor.loadMembers(token, chatId, this);
    }

    @Override
    public void loadCurrentUser(String token) {
        chatInteractor.loadCurrentUser(token, this);
    }

    @Override
    public void onSuccessChat(ChatViewModel chat) {
        if(chatView != null){
            chatView.initChat(chat);
        }
    }

    @Override
    public void onSuccessSend() {
        if (chatView != null){
            chatView.loadMessages();
        }
    }

    @Override
    public void onSuccessMessages(List<MessageViewModel> body) {
        if(chatView != null){
            chatView.hideProgress();
            chatView.initMessages(body);
        }
    }

    @Override
    public void onSuccessMembers(List<UserViewModel> body) {
        if(chatView != null){
            chatView.initMembers(body);
        }
    }

    @Override
    public void onSuccessUser(UserViewModel body) {
        if(chatView != null){
            chatView.initCurrentUser(body);
        }
    }

    @Override
    public void showError(String text) {
        if(chatView != null){
            chatView.hideProgress();
            chatView.showError(text);
        }
    }



}

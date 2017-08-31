package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.ChatViewModel;
import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

public class MainPresenterImpl implements MainPresenter, MainInteractor.OnLoadingFinishedListener {

    private MainView mainView;
    private MainInteractor mainInteractor;

    public MainPresenterImpl(MainView mainView) {
        this.mainView = mainView;
        this.mainInteractor = new MainInteractorImpl();
    }


    @Override
    public void fillUserData(String token) {
        if (mainView != null) {
            mainView.showProgress();
        }
        mainInteractor.fillUserData(token, this);
    }

    @Override
    public void loadChats(String token) {
        mainInteractor.loadChats(token,this);
    }

    @Override
    public void onSuccessUser(UserViewModel user) {
        if(mainView != null){
            mainView.fillCurrentUser(user);
        }
    }

    @Override
    public void onSuccessChats(List<ChatViewModel> chats) {
        if(mainView != null){
            mainView.hideProgress();
            mainView.fillChats(chats);
        }
    }

    @Override
    public void showError(String text) {
        if(mainView != null){
            mainView.showError(text);
        }
    }
}

package com.romanvytv.webapiandroidtest.presentation.friends;


import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

public class FriendsPresenterImpl implements FriendsPresenter, FriendsInteractor.OnLoadingFinishedListener {

    private final FriendsInteractorImpl friendsInteractor;
    private FriendsView friendsView;


    public FriendsPresenterImpl(FriendsView friendsView) {
        this.friendsView = friendsView;
        this.friendsInteractor = new FriendsInteractorImpl();
    }

    @Override
    public void loadFriends(String token) {
        if(friendsView != null){
            friendsView.showProgress();
        }
        friendsInteractor.loadFriends(token, this);
    }

    @Override
    public void onSuccess(List<UserViewModel> friends) {
        if (friendsView != null){
            friendsView.hideProgress();
            friendsView.fillFriends(friends);
        }
    }

    @Override
    public void showError(String text) {
        if(friendsView != null){
            friendsView.hideProgress();
            friendsView.showError(text);
        }
    }
}

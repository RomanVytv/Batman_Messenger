package com.romanvytv.webapiandroidtest.presentation.user;

import com.romanvytv.webapiandroidtest.models.UserViewModel;



public class UserPresenterImpl implements UserPresenter, UserInteractor.OnLoadingFinishedListener {

    private UserView userView;
    private UserInteractor userInteractor;

    public UserPresenterImpl(UserView userView) {
        this.userView = userView;
        userInteractor = new UserInteractorImpl();
    }

    @Override
    public void loadUser(String token, String userId) {
        if(userView != null){
         userView.showProgress();
        }
        userInteractor.loadUser(token,userId,this);
    }

    @Override
    public void onSuccess(UserViewModel user) {
        if(userView != null){
            userView.hideProgress();
            userView.fillUser(user);
        }
    }

    @Override
    public void showError(String text) {

    }
}

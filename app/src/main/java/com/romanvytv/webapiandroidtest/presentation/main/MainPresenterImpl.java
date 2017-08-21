package com.romanvytv.webapiandroidtest.presentation.main;


import com.romanvytv.webapiandroidtest.models.UserViewModel;

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
    public void onSuccess(UserViewModel user) {
        if(mainView != null){
            mainView.hideProgress();
            mainView.fillCurrentUser(user);

        }

    }

    @Override
    public void showError(String text) {
        if(mainView != null){
            //mainView.hideProgress();
            mainView.showError(text);
        }
    }
}

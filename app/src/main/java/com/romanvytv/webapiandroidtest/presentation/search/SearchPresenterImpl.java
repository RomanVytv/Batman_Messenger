package com.romanvytv.webapiandroidtest.presentation.search;


import com.romanvytv.webapiandroidtest.models.UserViewModel;

import java.util.List;

public class SearchPresenterImpl implements SearchPresenter, SearchInterceptor.OnLoadingFinishedListener {

    private SearchInterceptor searchInterceptor;
    private ISearchView searchView;

    public SearchPresenterImpl(ISearchView view) {
        this.searchView = view;
        searchInterceptor = new SearchInterceptorImpl();
    }

    @Override
    public void find(String token, String type, String value) {
        if(searchView != null){
            searchView.showProgress();
        }
        switch (type){
            case "by email":
                searchInterceptor.findByEmail(token,value,this);
                break;
            case "by name":
                searchInterceptor.findByName(token,value,this);
                break;
            case "by surname":
                searchInterceptor.findBySurname(token,value,this);
                break;
            case "by phone":
                searchInterceptor.findByPhone(token,value,this);
                break;
                default:
                    searchInterceptor.findByEmail(token,value,this);
        }
    }

    @Override
    public void addFriend(String token, String chatName, String friendId) {
        if(searchView != null){
            searchView.showProgress();
        }
        searchInterceptor.addFriend(token,chatName,friendId,this);
    }

    @Override
    public void onSuccessSearch(List<UserViewModel> users) {
        if(searchView != null){
            searchView.hideProgress();
            searchView.fillUsers(users);
        }

    }

    @Override
    public void onSuccessAddFriend() {
        if(searchView != null){
            searchView.hideProgress();
            searchView.navigateToMain();
        }
    }

    @Override
    public void showError(String text) {
        if(searchView != null){
            searchView.hideProgress();
            searchView.showError(text);
        }
    }
}

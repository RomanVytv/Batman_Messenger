package com.romanvytv.webapiandroidtest.presentation.register;

import com.romanvytv.webapiandroidtest.models.RegisterModel;


public interface RegisterPresenter {

    void validateData(RegisterModel registerModel);

    void onDestroy();

}




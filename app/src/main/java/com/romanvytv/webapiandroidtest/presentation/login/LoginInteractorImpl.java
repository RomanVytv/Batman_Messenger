package com.romanvytv.webapiandroidtest.presentation.login;


import com.romanvytv.webapiandroidtest.data.api.AccountService;
import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.models.TokenModel;
import com.romanvytv.webapiandroidtest.utils.EmailValidator;
import com.romanvytv.webapiandroidtest.utils.PasswordValidator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginInteractorImpl implements LoginInteractor {


    @Override
    public void login(String email, String password, final OnLoginFinishedListener listener) {
        AccountService accountService = ApiServiceCreator.createService(AccountService.class);
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean error = false;

        if (!emailValidator.validate(email)){
            listener.onEmailError();
            error = true;
        }
        if (!passwordValidator.validate(password)){
            listener.onPasswordError();
            error = true;
        }
        if (!error){
            accountService.getToken("password", email, password)
                    .enqueue(new Callback<TokenModel>() {
                        @Override
                        public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                            String token = "";
                            if(response.body() != null && response.isSuccessful()) {
                                token = "Bearer " + response.body().getAccessToken();
                                listener.onSuccess(token);
                            }else{
                                listener.showError(response.message() + "\n Invalid email or password");
                            }
                        }

                        @Override
                        public void onFailure(Call<TokenModel> call, Throwable t) {
                            listener.showError(t.getMessage());
                        }
                    });

        }
    }
}

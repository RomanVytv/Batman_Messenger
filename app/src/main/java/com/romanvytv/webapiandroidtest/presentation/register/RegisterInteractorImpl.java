package com.romanvytv.webapiandroidtest.presentation.register;

import android.text.TextUtils;

import com.romanvytv.webapiandroidtest.data.api.AccountService;
import com.romanvytv.webapiandroidtest.data.api.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.models.RegisterModel;
import com.romanvytv.webapiandroidtest.utils.EmailValidator;
import com.romanvytv.webapiandroidtest.utils.PasswordValidator;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterInteractorImpl implements RegisterInteraptor {
    @Override
    public void register(RegisterModel model, final RegisterInteraptor.OnRegisterFinishedListener listener) {
        AccountService accountService = ApiServiceCreator.createService(AccountService.class);
        EmailValidator emailValidator = new EmailValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean error = false;

        if (!emailValidator.validate(model.getEmail())){
            listener.onEmailError();
            error = true;
            return;
        }
        if (!passwordValidator.validate(model.getPassword())){
            listener.onPasswordError();
            error = true;
            return;
        }

        if (!(model.getPassword().equals(model.getConfirmPassword()))){
            listener.onConfirmPasswordError();
            error = true;
            return;
        }
        if(TextUtils.isEmpty(model.getName())){
            listener.onNameError();
            error = true;
            return;
        }
        if(TextUtils.isEmpty(model.getSurname())){
            listener.onSurnameError();
            error = true;
            return;
        }
        // TODO: make phone validator
        if(TextUtils.isEmpty(model.getPhoneNumber())){
            listener.onPhoneNumberError();
            error = true;
            return;
        }
        if(TextUtils.isEmpty(model.getBornDate())){
            listener.onBirthDateError();
            error = true;
            return;
        }
        if(!error){
            accountService.register(model).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response != null && response.isSuccessful()){
                        listener.showError("Registration successful! " +  response.message());
                        listener.onSuccess();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    listener.showError(t.getMessage());
                }
            });
        }




    }
}

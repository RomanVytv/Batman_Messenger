package com.romanvytv.webapiandroidtest.presentation.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.orhanobut.hawk.Hawk;
import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.presentation.main.MainActivity;
import com.romanvytv.webapiandroidtest.presentation.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements LoginView  {

    @BindView(R.id.progressLogin) ProgressBar progressBar;
    @BindView(R.id.editEmailLogin) EditText email;
    @BindView(R.id.editPasswordLogin) EditText password;
    @BindView(R.id.btnSignIn) Button signIn;
    @BindView(R.id.btnRegister) Button signUp;
    @BindView(R.id.keepSignedIn) CheckBox keepSignedInCheckBox;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this);

    }


    @OnClick(R.id.btnSignIn)
    public void  signIn(){
        presenter.validateCredentials(email.getText().toString(), password.getText().toString());
    }

    @OnClick(R.id.btnRegister)
    public void  register(){
        navigateToRegister();
        finish();
    }


    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setEmailError() {
        email.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {
        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome(String token) {
        if(keepSignedInCheckBox.isChecked())
            Hawk.put("authToken", token);

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("TOKEN", token);
        startActivity(intent);
        finish();
    }

    @Override
    public void navigateToRegister() {
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }

    @Override
    public void showError(String text) {
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }
}



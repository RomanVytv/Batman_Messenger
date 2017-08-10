package com.romanvytv.webapiandroidtest.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.TokenModel;
import com.romanvytv.webapiandroidtest.web.AccountService;
import com.romanvytv.webapiandroidtest.web.ApiServiceCreator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static AccountService accountService;
    private TextView email;
    private TextView password;
    private Button signIn;
    private Button signUp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        email = (TextView) findViewById(R.id.editEmailLogin);
        password = (TextView) findViewById(R.id.editPasswordLogin);
        signIn = (Button) findViewById(R.id.btnSignIn);
        signUp = (Button) findViewById(R.id.btnRegister);
        accountService = ApiServiceCreator.createService(AccountService.class);
        sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountService.getToken("password", email.getText().toString(),password.getText().toString())
                        .enqueue(new Callback<TokenModel>() {
                            @Override
                            public void onResponse(Call<TokenModel> call, Response<TokenModel> response) {
                                if(response.body() != null) {
                                    token = "Bearer " + response.body().getAccessToken();
                                }
                                editor = sharedPreferences.edit();
                                editor.putString("TOKEN",token);
                                editor.apply();
                                Toast.makeText(getApplicationContext(),"Token response -" + response.message(),Toast.LENGTH_LONG).show();
                                loginWithToken(token);
                            }

                            @Override
                            public void onFailure(Call<TokenModel> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "An error occurred:" + t.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });



    }

    private void loginWithToken(String token){
        if((token != null)){
            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra("TOKEN",token);
            startActivity(intent);
        }
    }
}

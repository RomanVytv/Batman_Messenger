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

import com.romanvytv.webapiandroidtest.web.ApiServiceCreator;
import com.romanvytv.webapiandroidtest.web.UserService;
import com.romanvytv.webapiandroidtest.models.UserViewModel;
import com.romanvytv.webapiandroidtest.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static UserService userService;
    private TextView textView;
    private String token;
    private SharedPreferences sharedPreferences;
    private Button btnLogout;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("TOKEN", Context.MODE_PRIVATE);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        textView = (TextView) findViewById(R.id.textView);

        if(getIntent().getStringExtra("TOKEN") == null){
            token = tryGetToken();
        }else {
            token = getIntent().getStringExtra("TOKEN");
        }

        fillUserData(token);


        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences.edit().clear().apply();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
    }

    private String tryGetToken(){
        String token = null;
        try{
            token = sharedPreferences.getString("TOKEN","");

        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Token not found!" + e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return token;
    }

    private void fillUserData(String token){
        if(!(token.isEmpty())){
            userService = ApiServiceCreator.createService(UserService.class,token);
            userService.getCurrentUser().enqueue(new Callback<UserViewModel>() {
                @Override
                public void onResponse(Call<UserViewModel> call, Response<UserViewModel> response) {
                    if(response.body() != null){
                        textView.setText(response.body().toString());
                    }else{
                        Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<UserViewModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
        }else{
            Intent intent = new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }
}

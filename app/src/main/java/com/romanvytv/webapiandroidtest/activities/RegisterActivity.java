package com.romanvytv.webapiandroidtest.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.RegisterModel;
import com.romanvytv.webapiandroidtest.web.AccountService;
import com.romanvytv.webapiandroidtest.web.ApiServiceCreator;

import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity {

    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private EditText name;
    private EditText surname;
    private EditText bornDate;
    private EditText phoneNumber;
    private Button btnSignUp;
    private TextView backBtn;
    final Calendar myCalendar = Calendar.getInstance();

    private AccountService accountService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.editEmail);
        password = (EditText) findViewById(R.id.editPassword);
        confirmPassword = (EditText) findViewById(R.id.editConfirmPassword);
        name = (EditText) findViewById(R.id.editName);
        surname = (EditText) findViewById(R.id.editSurname);
        bornDate = (EditText) findViewById(R.id.editBirthDate);
        phoneNumber = (EditText) findViewById(R.id.editPhoneNumber);
        btnSignUp = (Button) findViewById(R.id.btnSignUpReg);
        backBtn = (TextView) findViewById(R.id.tvBackBtn);
        accountService = ApiServiceCreator.createService(AccountService.class);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                bornDate.setText(dayOfMonth + "." +(monthOfYear+1) + "." +year);
            }

        };

        bornDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(RegisterActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });




        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                accountService.register(new RegisterModel(
                        email.getText().toString(),
                        password.getText().toString(),
                        confirmPassword.getText().toString(),
                        name.getText().toString(),
                        surname.getText().toString(),
                        bornDate.getText().toString(),
                        phoneNumber.getText().toString()
                )).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"Fail in onResponce() " + response.message(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"Register failed: " + t.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
            }
        });



    }


}

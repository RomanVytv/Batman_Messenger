package com.romanvytv.webapiandroidtest.presentation.register;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.romanvytv.webapiandroidtest.R;
import com.romanvytv.webapiandroidtest.models.RegisterModel;
import com.romanvytv.webapiandroidtest.presentation.login.LoginActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import info.hoang8f.android.segmented.SegmentedGroup;

public class RegisterActivity extends Activity implements RegisterView {

    @BindView(R.id.progressRegister) ProgressBar progressBar;
    @BindView(R.id.editEmail) EditText email;
    @BindView(R.id.editPassword) EditText password;
    @BindView(R.id.editConfirmPassword) EditText confirmPassword;
    @BindView(R.id.editName) EditText name;
    @BindView(R.id.editSurname) EditText surname;
    @BindView(R.id.editPhoneNumber) EditText phoneNumber;
    @BindView(R.id.editBirthDate) EditText bornDate;
    @BindView(R.id.genderBtn) SegmentedGroup gender;
    @BindView(R.id.genderMale) RadioButton male;
    @BindView(R.id.genderFemale) RadioButton female;
    @BindView(R.id.btnSignUpReg) Button btnSignUp;

    final Calendar myCalendar = Calendar.getInstance();

    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        presenter = new RegisterPresenterImpl(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        initBornDate();
        initGenderSegment();
    }

    private void initBornDate() {

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                bornDate.setText(dayOfMonth + "." + (monthOfYear + 1) + "." + year);
            }

        };

        bornDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(RegisterActivity.this, R.style.CustonDateDialogTheme, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void initGenderSegment() {
        male.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal800));
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if (i == R.id.genderMale) {
                    male.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal800));
                    female.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
                } else {
                    female.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal800));
                    male.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.colorTeal50));
                }
            }
        });
    }

    @Override
    public void register() {
        presenter.validateData(new RegisterModel(
                email.getText().toString(),
                password.getText().toString(),
                confirmPassword.getText().toString(),
                name.getText().toString(),
                getGender(),
                surname.getText().toString(),
                bornDate.getText().toString(),
                phoneNumber.getText().toString()));
    }

    private String getGender() {
        int checkedResId = gender.getCheckedRadioButtonId();
        if (checkedResId == R.id.genderMale) {
            return "male";
        } else if (checkedResId == R.id.genderFemale) {
            return "female";
        }
        return "not given";
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @OnClick(R.id.backToLoginBtn)
    @Override
    public void navigateToLogin() {
        startActivity(new Intent(this, LoginActivity.class));
        finish();
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
    public void setConfirmPasswordError() {
        confirmPassword.setError(getString(R.string.passwords_not_match));
    }

    @Override
    public void setNameError() {
        name.setError(getString(R.string.invalid_name));
    }

    @Override
    public void setSurnameError() {
        surname.setError(getString(R.string.invalid_surname));
    }

    @Override
    public void setPhoneNumberError() {
        phoneNumber.setError(getString(R.string.invalid_phone));
    }

    @Override
    public void setBirthDateError() {
        bornDate.setError(getString(R.string.invalid_bornDate));
    }

    @Override
    public void showError(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
    }
}
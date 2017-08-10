package com.romanvytv.webapiandroidtest.models;

/**
 * Created by Роман on 07.08.2017.
 */

public class LoginModel {

    private String grant_type = "password";
    private String email;
    private String password;

    public LoginModel(String grant_type, String email, String password) {
        this.email = email;
        this.password = password;
        this.grant_type = grant_type;
    }

    public String getGrant_type() {
        return email;
    }

    public void setGrant_type(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}

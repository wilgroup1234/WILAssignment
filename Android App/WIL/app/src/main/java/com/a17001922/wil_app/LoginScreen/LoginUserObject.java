package com.a17001922.wil_app.LoginScreen;
/*
#TODO THIS IS THE LOGIN MODEL FOR USERS THAT WE USE ANY INCLUDED FILEDS ON THE DATABASE SIDE WILL NEED TO BE ADDED INTO THIS CLASS AS WELL
 */
public class LoginUserObject {
    protected String email;
    protected String password;

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

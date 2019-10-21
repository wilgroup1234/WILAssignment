package com.a17001922.wil_app.LoginScreen;
/*
#TODO THIS IS THE LOGIN MODEL FOR USERS THAT WE USE ANY INCLUDED FILEDS ON THE DATABASE SIDE WILL NEED TO BE ADDED INTO THIS CLASS AS WELL
 */
//This class is an Object used to send/ receive data from the API
public class LoginUserObject
{
    //_____________Declarations_________________
    protected String email;
    protected String password;

    //_____________Get and Set methods_________________

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

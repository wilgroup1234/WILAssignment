package com.a17001922.wil_app.LoginScreen;
/*
#TODO THIS IS THE MODEL FOR THE REGISTERATION OF A USER IF ANY NEW FIELDS ARE ADDED DATABASE SIDE NEW FIELDS WILL BE REQUIRED IN THIS SECTION
 */
public class RegisterUserObject {
    protected int userID;
    protected String firstName,LastName,email,password,confirmPassword;
    protected int age;


    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return LastName;
    }

    public void setSurname(String surname) {
        this.LastName = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
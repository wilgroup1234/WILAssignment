package com.a17001922.wil_app.LoginScreen;
/*
#TODO THIS IS THE MODEL FOR THE REGISTRATION OF A USER IF ANY NEW FIELDS ARE ADDED DATABASE SIDE NEW FIELDS WILL BE REQUIRED IN THIS SECTION
 */

//This class is an Object used to send/ receive data from the API
public class RegisterUserObject
{
    //_____________Declarations_________________
    private int userID;
    private String firstName,LastName,email,password,confirmPassword, SecurityQuestion, Answer;
    private int age;

    //_____________Get and Set methods_________________

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

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getSecurityQuestion() {
        return SecurityQuestion;
    }

    public void setSecurityQuestion(String securityQuestion) {
        SecurityQuestion = securityQuestion;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        this.confirmPassword = confirmPassword;
    }
}

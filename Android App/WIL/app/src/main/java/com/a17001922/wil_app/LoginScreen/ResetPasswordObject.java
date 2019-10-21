package com.a17001922.wil_app.LoginScreen;

//This class is an Object used to send/ receive data from the API
public class ResetPasswordObject
{
    //_____________Declarations_________________
    private String Question, Answer, Email, NewPassword;

    //_____________Get and Set methods_________________

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String newPassword) {
        NewPassword = newPassword;
    }
}


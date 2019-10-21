package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API

public class LeaderboardObject
{

    //_____________Declarations_________________
    private int Score;
    private String Email;


    //_____________Get and Set methods_________________
    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

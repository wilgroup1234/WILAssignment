package com.a17001922.wil_app.goals;
//TODO THIS IS THE MODEL CLASS FOR A CUSTOM GOAL THE USER CREATES

import com.google.api.client.util.DateTime;

//This class is an Object used to send/ receive data from the API
public class CustomGoalObject
{

    //_____________Declarations_________________
    private String goalName;
    private String goalDescription;
    private String Email;
    private String finishDate;

    //_____________Get and Set methods_________________

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription)
    {
        this.goalDescription = goalDescription;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }
}

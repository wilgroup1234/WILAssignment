package com.a17001922.wil_app.goals;
//TODO THIS IS THE MODEL CLASS FOR A CUSTOM GOAL THE USER CREATES

import com.google.api.client.util.DateTime;

public class CustomGoalObject {
    private String goalName;
    private String goalDescription;
    private String Email;
    private String finishDate;

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
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

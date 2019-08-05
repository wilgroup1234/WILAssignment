package com.a17001922.wil_logins.goals;
//TODO THIS IS THE MODEL CLASS FOR A CUSTOM GOAL THE USER CREATES

public class customGoal {
    protected String goalName;
    protected String goalDescription;
    protected String Email;

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
}

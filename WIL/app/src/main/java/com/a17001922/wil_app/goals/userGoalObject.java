package com.a17001922.wil_app.goals;
//#TODO THIS IS THE SET GOAL OBJECT FOR ALREADY DEFINED GOALS GIVEN IF THE USERS JUST WANT TO QUICK SELECT A GOAL


public class userGoalObject {
    protected String Email;
    protected String GoalId;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGoalId() {
        return GoalId;
    }

    public void setGoalId(String goalId) {
        GoalId = goalId;
    }
}

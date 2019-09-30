package com.a17001922.wil_app.goals;
//#TODO THIS IS THE SET GOAL OBJECT FOR ALREADY DEFINED GOALS GIVEN IF THE USERS JUST WANT TO QUICK SELECT A GOAL


import com.google.api.client.util.DateTime;

public class UserGoalObject
{
    private String Email;
    private int GoalId;
    private DateTime finishDate;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public DateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(DateTime finishDate) {
        this.finishDate = finishDate;
    }

    public int getGoalId() {
        return GoalId;
    }

    public void setGoalId(int goalId) {
        GoalId = goalId;
    }
}

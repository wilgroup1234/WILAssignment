package com.a17001922.wil_app.goals;
//TODO THIS IS THE MODEL CLASS FOR A CUSTOM GOAL THE USER CREATES
import com.google.api.client.util.DateTime;
public class customGoalObject {
    protected String goalName;
    protected String goalDescription;
    protected String Email;
    protected DateTime finishDate;

    public DateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(DateTime finishDate) {
        this.finishDate = finishDate;
    }



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

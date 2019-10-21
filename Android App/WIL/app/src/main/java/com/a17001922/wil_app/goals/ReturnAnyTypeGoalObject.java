package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API
public class ReturnAnyTypeGoalObject
{

    //_____________Declarations_________________
    private int GoalID;
    private String GoalName;
    private String GoalDescription;
    private int Completed;
    private Boolean isNormalGoal;
    private String finishDate;
    private String currentDate;



    //_____________Get and Set methods_________________
    public int getGoalID() {
        return GoalID;
    }

    public void setGoalID(int goalID) {
        GoalID = goalID;
    }

    public String getGoalName() {
        return GoalName;
    }

    public void setGoalName(String goalName) {
        GoalName = goalName;
    }

    public String getGoalDescription() {
        return GoalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        GoalDescription = goalDescription;
    }

    public int getCompleted() {
        return Completed;
    }

    public void setCompleted(int completed) {
        Completed = completed;
    }

    public Boolean getNormalGoal() {
        return isNormalGoal;
    }

    public void setNormalGoal(Boolean normalGoal) {
        isNormalGoal = normalGoal;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
}

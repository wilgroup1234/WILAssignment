package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API
public class Goal
{

    //_____________Declarations_________________
    private int GoalID;
    private String GoalName;
    private String GoalDescription;


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
}

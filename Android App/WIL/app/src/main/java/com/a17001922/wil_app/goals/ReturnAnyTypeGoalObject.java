package com.a17001922.wil_app.goals;

public class ReturnAnyTypeGoalObject
{
    private int GoalID;
    private String GoalName;
    private String GoalDescription;
    private int Completed;
    private Boolean isNormalGoal;

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
}

package com.a17001922.wil_app.homeScreen;

//This class is an Object used to send/ receive data from the API
public class GoalsCheckedClass
{
    //_____________Declarations_________________
    private boolean checked;
    private int GoalID;
    private boolean isNormalGoal;

    //_____________Get and Set methods_________________

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public int getGoalID()
    {
        return GoalID;
    }

    public void setGoalID(int goalID)
    {
        GoalID = goalID;
    }

    public boolean isNormalGoal()
    {
        return isNormalGoal;
    }

    public void setNormalGoal(boolean normalGoal)
    {
        isNormalGoal = normalGoal;
    }
}

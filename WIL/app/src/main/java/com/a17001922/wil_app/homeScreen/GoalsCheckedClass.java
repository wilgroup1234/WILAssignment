package com.a17001922.wil_app.homeScreen;

public class GoalsCheckedClass
{
    private boolean checked;

    private int GoalID;

    private boolean isNormalGoal;

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

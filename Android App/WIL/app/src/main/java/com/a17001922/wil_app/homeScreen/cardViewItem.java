package com.a17001922.wil_app.homeScreen;

public class cardViewItem
{
    private int imageResource;
    private String goalName;
    private String goalDescription;
    private boolean completed;

    public cardViewItem(int imageResource, String goalName, String goalDescription, boolean completed)
    {
        this.imageResource = imageResource;
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.completed = completed;
    }

    public int getImageResource()
    {
        return imageResource;
    }

    public String getGoalName()
    {
        return goalName;
    }

    public String getGoalDescription()
    {
        return goalDescription;
    }

    public boolean isCompleted()
    {
        return completed;
    }
}

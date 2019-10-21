package com.a17001922.wil_app.homeScreen;

//This class is a template used for goals card Items
public class cardViewItem
{
    //_____________Declarations_________________
    private int imageResource;
    private String goalName;
    private String goalDescription;
    private boolean completed;
    private String date;

    //_____________Get and Set methods_________________
    public cardViewItem(int imageResource, String goalName, String goalDescription, boolean completed, String date)
    {
        this.imageResource = imageResource;
        this.goalName = goalName;
        this.goalDescription = goalDescription;
        this.completed = completed;
        this.date = date;
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

    public String getDate()
    {
        return date;
    }
}

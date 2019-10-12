package com.a17001922.wil_app.goals;

public class DeleteGoalObject
{
    private Boolean isNormal;

    private int goalID;

    private String email;

    public Boolean getNormal()
    {
        return isNormal;
    }

    public void setNormal(Boolean normal)
    {
        isNormal = normal;
    }

    public int getGoalID()
    {
        return goalID;
    }

    public void setGoalID(int goalID)
    {
        this.goalID = goalID;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}

package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API
public class DeleteGoalObject
{

    //_____________Declarations_________________
    private Boolean isNormal;
    private int goalID;
    private String email;


    //_____________Get and Set methods_________________
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

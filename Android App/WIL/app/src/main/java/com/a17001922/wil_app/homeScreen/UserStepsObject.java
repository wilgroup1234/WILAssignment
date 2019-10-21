package com.a17001922.wil_app.homeScreen;

//This class is an Object used to send/ receive data from the API
public class UserStepsObject
{
    //_____________Declarations_________________
    private String Email;
    private int numSteps;

    //_____________Get and Set methods_________________
    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public int getNumSteps()
    {
        return numSteps;
    }

    public void setNumSteps(int numSteps)
    {
        this.numSteps = numSteps;
    }
}

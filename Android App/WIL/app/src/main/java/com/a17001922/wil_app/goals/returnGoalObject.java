package com.a17001922.wil_app.goals;

import java.util.List;

// #TODO THIS IS THE LIST OF GOALS THAT WE RETRIEVE FROM THE DATABASE FOR A GIVEN USER

//This class is an Object used to send/ receive data from the API
public class ReturnGoalObject
{

    //_____________Declarations_________________
    private List<Goal> goalList;


    //_____________Get and Set methods_________________
    public List<Goal> getGoalList()
    {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList)
    {
        this.goalList = goalList;
    }
}

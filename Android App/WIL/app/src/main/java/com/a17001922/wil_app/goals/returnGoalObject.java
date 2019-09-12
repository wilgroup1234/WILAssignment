package com.a17001922.wil_app.goals;

import java.util.List;

// #TODO THIS IS THE LIST OF GOALS THAT WE RETRIEVE FROM THE DATABASE FOR A GIVEN USER


public class returnGoalObject
{
    private List<Goal> goalList;

    public List<Goal> getGoalList()
    {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList)
    {
        this.goalList = goalList;
    }
}

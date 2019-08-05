package com.a17001922.wil_logins.goals;

import java.util.List;

// #TODO THIS IS THE LIST OF GOALS THAT WE RETRIEVE FROM THE DATABASE FOR A GIVEN USER


public class returnGoal {
    protected List<goals> goalList;

    public List<goals> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<goals> goalList) {
        this.goalList = goalList;
    }
}

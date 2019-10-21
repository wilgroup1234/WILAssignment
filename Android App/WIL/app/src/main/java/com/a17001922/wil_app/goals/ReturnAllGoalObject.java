package com.a17001922.wil_app.goals;

import java.util.List;

//This class is an Object used to send/ receive data from the API
public class ReturnAllGoalObject
{

    //_____________Declarations_________________
    private List<ReturnAnyTypeGoalObject> goalList;


    //_____________Get and Set methods_________________

    public List<ReturnAnyTypeGoalObject> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<ReturnAnyTypeGoalObject> goalList) {
        this.goalList = goalList;
    }
}

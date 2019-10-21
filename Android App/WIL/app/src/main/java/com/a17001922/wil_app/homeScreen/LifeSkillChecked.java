package com.a17001922.wil_app.homeScreen;

//This class is an Object used to send/ receive data from the API
public class LifeSkillChecked
{
    //_____________Declarations_________________
    private int lifeSkillID;
    private boolean isCompleted;

    //_____________Get and Set methods_________________
    public int getLifeSkillID()
    {
        return lifeSkillID;
    }

    public void setLifeSkillID(int lifeSkillID)
    {
        this.lifeSkillID = lifeSkillID;
    }

    public boolean isCompleted()
    {
        return isCompleted;
    }

    public void setCompleted(boolean completed)
    {
        isCompleted = completed;
    }
}

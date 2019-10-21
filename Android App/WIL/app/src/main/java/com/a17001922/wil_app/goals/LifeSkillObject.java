package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API

public class LifeSkillObject
{

    //_____________Declarations_________________
    private String Email;
    private int LifeSkillID;
    private String LifeSkillName;
    private int Completed;


    //_____________Get and Set methods_________________
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public int getLifeSkillID()
    {
        return LifeSkillID;
    }

    public void setLifeSkillID(int lifeSkillID)
    {
        LifeSkillID = lifeSkillID;
    }

    public String getLifeSkillName()
    {
        return LifeSkillName;
    }

    public void setLifeSkillName(String lifeSkillName)
    {
        LifeSkillName = lifeSkillName;
    }

    public int getCompleted()
    {
        return Completed;
    }

    public void setCompleted(int completed)
    {
        Completed = completed;
    }
}

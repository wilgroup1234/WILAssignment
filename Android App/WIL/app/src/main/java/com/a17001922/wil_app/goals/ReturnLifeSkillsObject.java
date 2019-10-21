package com.a17001922.wil_app.goals;

import java.util.List;

//This class is an Object used to send/ receive data from the API
public class ReturnLifeSkillsObject
{

    //_____________Declarations_________________
    private List<LifeSkillObject> LifeSkillsList;


    //_____________Get and Set methods_________________
    public List<LifeSkillObject> getLifeSkillsList()
    {
        return LifeSkillsList;
    }

    public void setLifeSkillsList(List<LifeSkillObject> lifeSkillsList)
    {
        LifeSkillsList = lifeSkillsList;
    }
}

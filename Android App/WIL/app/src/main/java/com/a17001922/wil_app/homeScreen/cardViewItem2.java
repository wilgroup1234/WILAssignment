package com.a17001922.wil_app.homeScreen;

//This class is a template used for life skill card Items
public class cardViewItem2
{
    //_____________Declarations_________________
    private int imageResource;
    private String lifeSkillName;
    private boolean completed;

    //_____________Get and Set methods_________________
    public cardViewItem2(int imageResource, String lifeSkillName, boolean completed)
    {
        this.imageResource = imageResource;
        this.lifeSkillName = lifeSkillName;
        this.completed = completed;
    }

    public int getImageResource()
    {
        return imageResource;
    }


    public boolean isCompleted()
    {
        return completed;
    }

    public String getLifeSkillName()
    {
        return lifeSkillName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

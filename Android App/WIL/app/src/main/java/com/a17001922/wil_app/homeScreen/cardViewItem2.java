package com.a17001922.wil_app.homeScreen;

public class cardViewItem2
{
    private int imageResource;
    private String lifeSkillName;
    private boolean completed;

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

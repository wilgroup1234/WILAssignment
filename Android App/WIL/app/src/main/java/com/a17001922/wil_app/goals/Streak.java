package com.a17001922.wil_app.goals;

//This class is an Object used to send/ receive data from the API
public class Streak
{

    //_____________Declarations_________________
    private int StreakID;
    private int UserID;
    private int StreakLength;


    //_____________Get and Set methods_________________

    public int getStreakID()
    {
        return StreakID;
    }

    public void setStreakID(int streakID)
    {
        StreakID = streakID;
    }

    public int getUserID()
    {
        return UserID;
    }

    public void setUserID(int userID)
    {
        UserID = userID;
    }

    public int getStreakLength()
    {
        return StreakLength;
    }

    public void setStreakLength(int streakLength)
    {
        StreakLength = streakLength;
    }
}

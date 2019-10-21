package com.a17001922.wil_app.homeScreen;

//This class is an Object used to send/ receive data from the API
public class GratitudeObject
{
    //_____________Declarations_________________
    private String Email;
    private String Items;

    //_____________Get and Set methods_________________
    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getItems()
    {
        return Items;
    }

    public void setItems(String items)
    {
        Items = items;
    }
}

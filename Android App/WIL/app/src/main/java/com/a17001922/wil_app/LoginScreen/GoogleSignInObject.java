package com.a17001922.wil_app.LoginScreen;

//This class is an Object used to send/ receive data from the API
public class GoogleSignInObject
{
    //_____________Declarations_________________
    private String FirstName;
    private String LastName;
    private String Email;
    private String Password;

    //_____________Get and Set methods_________________
    public String getFirstName()
    {
        return FirstName;
    }

    public void setFirstName(String firstName)
    {
        FirstName = firstName;
    }

    public String getLastName()
    {
        return LastName;
    }

    public void setLastName(String lastName)
    {
        LastName = lastName;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }

    public String getPassword()

    {
        return Password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }
}

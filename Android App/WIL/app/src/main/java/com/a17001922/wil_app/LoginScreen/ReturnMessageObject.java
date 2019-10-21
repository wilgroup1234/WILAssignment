package com.a17001922.wil_app.LoginScreen;
/*
#TODO CLASS TO DEAL WITH THE RETURN MESSAGE FROM API ON POST CALLS. IF WE ADD ANY NEW FIELDS TO THE API CHANGES HERE WILL BE REQUIRED SO WE DONT HAVE ISSUES
 */

//This class is an Object used to send/ receive data from the API
public class ReturnMessageObject
{
    //_____________Declarations_________________
    protected boolean result;
    protected String errorMessage;

    //_____________Get and Set methods_________________

    public boolean getResult()
    {
        return result;
    }

    public void setResult(boolean successfulExecution)
    {
        this.result = successfulExecution;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String loginMessage)
    {
        this.errorMessage = loginMessage;
    }
}
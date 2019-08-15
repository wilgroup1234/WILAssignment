package com.a17001922.wil_app.LoginScreen;
/*
#TODO CLASS TO DEAL WITH THE RETURN MESSAGE FROM API ON POST CALLS. IF WE ADD ANY NEW FIELDS TO THE API CHANGES HERE WILL BE REQUIRED SO WE DONT HAVE ISSUES
 */
public class ReturnMessageObject
{
    protected boolean result;
    protected String errorMessage;


    public boolean getResult()
    {
        return result;
    }

    public void setResult(boolean successfulExecution)
    {
        this.result = successfulExecution;
    }

    public String getLoginMessage()
    {
        return errorMessage;
    }

    public void setLoginMessage(String loginMessage)
    {
        this.errorMessage = loginMessage;
    }
}
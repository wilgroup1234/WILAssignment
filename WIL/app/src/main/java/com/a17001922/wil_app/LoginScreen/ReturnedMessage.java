package com.a17001922.wil_app.LoginScreen;
/*
#TODO CLASS TO DEAL WITH THE RETURN MESSAGE FROM API ON POST CALLS. IF WE ADD ANY NEW FIELDS TO THE API CHANGES HERE WILL BE REQUIRED SO WE DONT HAVE ISSUES
 */
public class ReturnedMessage {
    protected boolean result;
    protected String loginMessage;


    public boolean isSuccessfulExecution() {
        return result;
    }

    public void setSuccessfulExecution(boolean successfulExecution) {
        this.result = successfulExecution;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }
}
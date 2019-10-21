package com.a17001922.wil_app.dailyQuote;

import retrofit2.Call;
import retrofit2.http.GET;

//#TODO METHODS TO CALL FOR THE DAILY OBJECT CALLS ONLY HERE

//This class contains GET and POST methods that are used to send and receive data from the API.
//This class is an interface with the method signatures of methods used by the app that relate to the API

public interface DailyQuoteService
{
    @GET("api/values/GetDailyQuote")
    Call<DailyObject> getQuote();
}

package com.a17001922.wil_app.dailyQuote;

import retrofit2.Call;
import retrofit2.http.GET;

//#TODO METHODS TO CALL FOR THE DAILY OBJECT CALLS ONLY HERE

public interface DailyQuoteService {

    @GET("https://localhost44317/api/values/GetDailyQuote")
    Call<DailyQuote> getQuote();


}

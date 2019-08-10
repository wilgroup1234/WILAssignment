package com.a16003776.webapitestapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebAPI
{
    @GET("/api/values/getdailyquote")
    Call<DailyQuote> getDailyQuote();

}

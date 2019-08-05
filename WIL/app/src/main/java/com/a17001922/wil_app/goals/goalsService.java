package com.a17001922.wil_app.goals;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface goalsService {
    @POST("https://localhost:44317/api/values/PostRetrieveGoals")
    Call<returnGoal> getGoalsList(@Body goals Goals);
}

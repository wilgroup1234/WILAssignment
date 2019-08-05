package com.a17001922.wil_app.goals;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface goalsService {
    @POST("https://localhost:44317/api/values/PostRetrieveGoals")
    Call<returnGoal> getGoalsList(@Body goals Goals);

    @POST("https://localhost:44317/api/values/PostAddNormalGoal")
    Call<returnGoal>addingGoal(@Body userGoal usersGoal);

    @POST("https://localhost:44317/api/values/PostAddCustomGoal")
    Call<returnGoal> addingCustomGoal(@Body customGoal customsGoal);

    @POST("https://localhost:44317/api/values/PostMarkOffCustomGoal")
    Call<returnGoal> markOfCustomGoal(@Body customGoal markoffGoal);

    @POST("https://localhost:44317/api/values/PostMarkOffNormalGoal")
    Call<returnGoal> markOfCustomGoal(@Body userGoal markoffGoal);



}

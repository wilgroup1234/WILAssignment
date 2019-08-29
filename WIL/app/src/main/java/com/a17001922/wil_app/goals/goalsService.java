package com.a17001922.wil_app.goals;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface goalsService {
    @POST("https://api/values/PostRetrieveGoals")
    Call<returnGoalObject> getGoalsList(@Body userGoalObject Goals);

    @POST("https://api/values/PostAddNormalGoal")
    Call<goals> addingGoal(@Body userGoalObject usersGoal);

    @POST("https://api/values/PostAddCustomGoal")
    Call<goals> addingCustomGoal(@Body customGoalObject customsGoal);

    @POST("https://api/values/PostMarkOffCustomGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body customGoalObject markoffGoal);

    @POST("https://api/values/PostMarkOffNormalGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body userGoalObject markoffGoal);


}

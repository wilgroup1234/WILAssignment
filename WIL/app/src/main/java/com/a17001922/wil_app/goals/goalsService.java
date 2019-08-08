package com.a17001922.wil_app.goals;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface goalsService {
    @POST("https://localhost:44317/api/values/PostRetrieveGoals")
    Call<returnGoalObject> getGoalsList(@Body goals Goals);

    @POST("https://localhost:44317/api/values/PostAddNormalGoal")
    Call<returnGoalObject>addingGoal(@Body userGoalObject usersGoal);

    @POST("https://localhost:44317/api/values/PostAddCustomGoal")
    Call<returnGoalObject> addingCustomGoal(@Body customGoalObject customsGoal);

    @POST("https://localhost:44317/api/values/PostMarkOffCustomGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body customGoalObject markoffGoal);

    @POST("https://localhost:44317/api/values/PostMarkOffNormalGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body userGoalObject markoffGoal);



}

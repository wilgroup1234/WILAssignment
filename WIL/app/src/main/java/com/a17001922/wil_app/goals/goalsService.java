package com.a17001922.wil_app.goals;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface goalsService {
    @POST("api/values/PostRetrieveGoals")
    Call<returnGoalObject> getGoalsList(@Body userGoalObject Goals);

    @POST("api/values/PostAddNormalGoal")
    Call<ReturnMessageObject> addingGoal(@Body userGoalObject usersGoal);

    @POST("api/values/PostAddCustomGoal")
    Call<ReturnMessageObject> addingCustomGoal(@Body customGoalObject customsGoal);

    @POST("api/values/PostMarkOffCustomGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body customGoalObject markoffGoal);

    @POST("api/values/PostMarkOffNormalGoal")
    Call<returnGoalObject> markOfCustomGoal(@Body userGoalObject markoffGoal);

    @GET("api/values/GetAllGoals")
    Call<returnGoalObject>getAllGoals();
}

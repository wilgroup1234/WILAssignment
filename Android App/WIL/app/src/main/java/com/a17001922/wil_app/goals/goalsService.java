package com.a17001922.wil_app.goals;

import com.a17001922.wil_app.LoginScreen.LoginUserObject;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.homeScreen.GratitudeObject;
import com.a17001922.wil_app.homeScreen.UserStepsObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface goalsService
{
    @POST("api/values/PostRetrieveGoals")
    Call<ReturnAllGoalObject> getGoalsList(@Body UserGoalObject Goals);

    @POST("api/values/PostAddNormalGoal")
    Call<ReturnMessageObject> addingGoal(@Body UserGoalObject usersGoal);

    @POST("api/values/GetAllGoals")
    Call<ReturnGoalObject> getAllGoals();

    @POST("api/values/PostAddCustomGoal")
    Call<ReturnMessageObject> addingCustomGoal(@Body CustomGoalObject customsGoal);

    @POST("api/values/PostMarkOffCustomGoal")
    Call<ReturnGoalObject> markOffCustomGoal(@Body CustomGoalObject markoffGoal);

    @POST("api/values/PostMarkOffNormalGoal")
    Call<ReturnGoalObject> markOfNormalGoal(@Body UserGoalObject markoffGoal);

    @POST("api/values/PostUserStreak")
    Call<Streak> getUserStreak(@Body LoginUserObject loginUserObject);

    @POST("api/values/PostUserSteps")
    Call<UserStepsObject> getUserSteps(@Body UserStepsObject userStepsObject);

    @POST("api/values/PostUpdateSteps")
    Call<ReturnMessageObject> updateStreak(@Body UserStepsObject userStepsObject);

    @POST("api/values/PostUserGratitude")
    Call<GratitudeObject> getUserGratitude(@Body GratitudeObject gratitudeObject);

    @POST("api/values/PostUpdateGratitude")
    Call<ReturnMessageObject> updateGratitude(@Body GratitudeObject gratitudeObject);


}

package com.a17001922.wil_app.LoginScreen;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/*
SIMPLE INTERFACE STRICTLY TO DEAL WITH USER LOGIN AND REGISTER NO OTHER CALLS TO BE ADDED HERE TO KEEP THIS SECTION CLEAN

 */
public interface loginRegisterService
{
    @POST("api/values/PostLogin")
    Call<ReturnMessageObject> userLogin(@Body LoginUserObject user);

    @POST("api/values/PostRegister")
    Call<ReturnMessageObject> userRegister(@Body RegisterUserObject user);

    @POST("api/values/PostGoogleSignIn")
    Call<ReturnMessageObject> googleSignIn(@Body GoogleSignInObject user);

    @POST("api/values/PostUpdateStreak")
    Call<ReturnMessageObject> updateStreak(@Body LoginUserObject user);



}

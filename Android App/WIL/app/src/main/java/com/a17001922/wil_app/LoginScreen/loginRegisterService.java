package com.a17001922.wil_app.LoginScreen;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


//This class contains GET and POST methods that are used to send and receive data from the API.
//This class is an interface with the method signatures of methods used by the app that relate to the API

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

    @POST("api/values/PostResetPassword")
    Call<ReturnMessageObject> resetPassword(@Body ResetPasswordObject resetPasswordObject);

}

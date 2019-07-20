package com.a17001922.wil_logins;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


/*
SIMPLE INTEFACE STRICTLY TO DEAL WITH USER LOGIN AND REGISTER NO OTHER CALLS TO BE ADDED HERE TO KEEP THIS SECTION CLEAN

 */
public interface loginRegisterService {
    @POST("api/values/PostLogin")
    Call<ReturnedMessage> userLogin(@Body LoginUser user);

    @POST("api/values/PostRegister")
    Call<ReturnedMessage> userRegister(@Body RegisterUser user);
}

package com.a17001922.wil_app.LoginScreen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/*
 NB!!! THIS WHOLE CLASS HAS TO DO STRICTLY WITH THE API CALLS AND GETTING OF AUTHORIZATION OF A USER TO LOGIN AND BE CREATED AS WELL AS CAN
 BE MANIPULATED TO INCLUDE ANYTHING ELSE WE WILL NEED TO DO ON THE DATABASE THROUGH THE API
 */
public class Connection {
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://localhost:44317/").build();
    loginRegisterService service = retrofit.create(loginRegisterService.class);
    private boolean loginAuth=false;
    private boolean registerAuth=false;

    public boolean userLogin(LoginUser user){

        final Call<ReturnedMessage> loginUserCall = service.userLogin(user);
        loginUserCall.enqueue(new Callback<ReturnedMessage>() {
            @Override
            public void onResponse(Call<ReturnedMessage> call, Response<ReturnedMessage> response) {
                if (!response.isSuccessful()){
                  loginAuth=false;
                }
                ReturnedMessage auth = response.body();

                if(auth.isSuccessfulExecution()){
                    loginAuth=true;
                }else{
                    loginAuth=false;
                }
            }

            @Override
            public void onFailure(Call<ReturnedMessage> call, Throwable t) {
                loginAuth=false;
            }
        });
       return loginAuth;
    }

    public boolean userRegister(RegisterUser user){

        final Call<ReturnedMessage>registerUserCall = service.userRegister(user);
        registerUserCall.enqueue(new Callback<ReturnedMessage>() {
            @Override
            public void onResponse(Call<ReturnedMessage> call, Response<ReturnedMessage> response) {
                if (!response.isSuccessful()){
                    registerAuth=false;
                }
                ReturnedMessage registeredAuth = response.body();
                if (registeredAuth.isSuccessfulExecution()){
                    registerAuth=true;
                }else{
                    registerAuth=false;
                }

            }

            @Override
            public void onFailure(Call<ReturnedMessage> call, Throwable t) {
                registerAuth=false;
            }
        });
        return registerAuth;
    }
}

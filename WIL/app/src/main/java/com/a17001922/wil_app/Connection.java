package com.a17001922.wil_app;

import android.util.Log;

import com.a17001922.wil_app.LoginScreen.LoginUserObject;
import com.a17001922.wil_app.LoginScreen.RegisterUserObject;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.LoginScreen.loginRegisterService;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.dailyQuote.DailyQuoteService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
/*
 NB!!! THIS WHOLE CLASS HAS TO DO STRICTLY WITH THE API CALLS AND GETTING OF AUTHORIZATION OF A USER TO LOGIN AND BE CREATED
 */

//#TODO TEST ALL METHODS IN THE CLASS TO SEE IF IT WORKS CORRECTLY WITH THE API
public class Connection {
    // #TODO IN ORDER TO TEST API LOCALLY CHANGE THE PORTION UNDER THIS TEXT TO YOUR MACHINES API ADDRESS
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://10.0.0.6:44317/").build();
    loginRegisterService service = retrofit.create(loginRegisterService.class);
    private boolean loginAuth=false;
    private boolean registerAuth=false;
    private DailyObject Quote;
    private static final String TAG = "ConnectionClass";

    public boolean userLogin(LoginUserObject user){
        Log.v(TAG,"I CALLED USER LOGIN");
        Log.e(TAG, "userLogin: ABOUT TO CREATE THE CALL" );
        final Call<ReturnMessageObject> loginUserCall = service.userLogin(user);
        loginUserCall.enqueue(new Callback<ReturnMessageObject>() {
            @Override
            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response) {
                try {
                    if (!response.isSuccessful()){
                        loginAuth=false;
                    }else{
                        ReturnMessageObject auth = response.body();

                        if(auth.getResult()){

                            loginAuth=true;
                            Log.e(TAG,auth.getLoginMessage() );
                        }else{
                            loginAuth=false;
                        }
                    }

                }catch(Exception e){
                    Log.e(TAG,e.toString());
                }

            }

            @Override
            public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                loginAuth=false;
                Log.e(TAG, "onFailure: "+ "its crashing in the call" );
            }
        });
       return loginAuth;
    }

    public boolean userRegister(RegisterUserObject user){

        final Call<ReturnMessageObject>registerUserCall = service.userRegister(user);
        registerUserCall.enqueue(new Callback<ReturnMessageObject>() {
            @Override
            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response) {
                if (!response.isSuccessful()){
                    registerAuth=false;
                }
                ReturnMessageObject registeredAuth = response.body();
                if (registeredAuth.getResult()){
                    registerAuth=true;
                }else{
                    registerAuth=false;
                }

            }

            @Override
            public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                registerAuth=false;
            }
        });
        return registerAuth;
    }

    public DailyObject getDailyQuote(){
          Quote = new DailyObject();
        DailyQuoteService service = retrofit.create(DailyQuoteService.class);
        final Call<DailyObject> quoteCall= service.getQuote();
        quoteCall.enqueue(new Callback<DailyObject>() {
            @Override
            public void onResponse(Call<DailyObject> call, Response<DailyObject> response) {
                if(!response.isSuccessful()){

                }else{
                    Quote=response.body();
                }
            }

            @Override
            public void onFailure(Call<DailyObject> call, Throwable t) {

            }
        });
        return Quote;
    }
}

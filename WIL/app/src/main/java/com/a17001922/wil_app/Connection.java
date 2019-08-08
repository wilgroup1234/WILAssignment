package com.a17001922.wil_app;

import android.util.Log;

import com.a17001922.wil_app.LoginScreen.LoginUserObject;
import com.a17001922.wil_app.LoginScreen.RegisterUserObject;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.LoginScreen.loginRegisterService;
import com.a17001922.wil_app.LoginScreen.number;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.dailyQuote.DailyQuoteService;
import com.a17001922.wil_app.goals.customGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.returnGoalObject;
import com.a17001922.wil_app.goals.userGoalObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/*
 NB!!! THIS WHOLE CLASS HAS TO DO STRICTLY WITH THE API CALLS AND GETTING OF AUTHORIZATION OF A USER TO LOGIN AND BE CREATED
 */

//#TODO TEST ALL METHODS IN THE CLASS TO SEE IF IT WORKS CORRECTLY WITH THE API
public class Connection {
    // #TODO IN ORDER TO TEST API LOCALLY CHANGE THE PORTION UNDER THIS TEXT TO YOUR MACHINES API ADDRESS
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://10.117.190.135:44317/").addConverterFactory(GsonConverterFactory.create()).build();
    loginRegisterService service = retrofit.create(loginRegisterService.class);
    private boolean loginAuth=false;
    private boolean registerAuth=false;
    private DailyObject Quote;
    private returnGoalObject goalsList;
    private userGoalObject addGoal;
    private customGoalObject addingCustomGoal;
    private boolean flag;
    private static final String TAG = "ConnectionClass";


    public boolean userLogin(LoginUserObject user){
        Log.v(TAG,"I CALLED USER LOGIN");
        Log.e(TAG, "userLogin: ABOUT TO CREATE THE CALL" );
        final Call <number> numberCall =service.number();
        Log.e(TAG,"Service added +36");
        numberCall.enqueue(new Callback<number>() {


            @Override
            public void onResponse(Call<number> call, Response<number> response) {
                if (response.isSuccessful()){
                    number num = response.body();
                    Log.e(TAG,"HERE IS THE NUMBER"+num.getNumber());
                }
            }

            @Override
            public void onFailure(Call<number> call, Throwable t) {
                Log.e(TAG, "onFailure: failed" ,t);
            }
        });
        /*final Call<ReturnMessageObject> loginUserCall = service.userLogin(user);
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
        });*/
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

    public returnGoalObject getGoals(userGoalObject userGoals){
        goalsList = new returnGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<returnGoalObject> goalsCall= service.getGoalsList(userGoals);
        goalsCall.enqueue(new Callback<returnGoalObject>() {
            @Override
            public void onResponse(Call<returnGoalObject> call, Response<returnGoalObject> response) {
                if(!response.isSuccessful()){

                }else{
                    goalsList=response.body();
                }
            }

            @Override
            public void onFailure(Call<returnGoalObject> call, Throwable t) {

            }
        });
        return goalsList;
    }

    public boolean addUserGoal(userGoalObject addingGoal){
         flag = false;
        addGoal = new userGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<userGoalObject> addGoalCall = service.addingGoal(addingGoal);
        addGoalCall.enqueue(new Callback<userGoalObject>() {
            @Override
            public void onResponse(Call<userGoalObject> call, Response<userGoalObject> response) {
                if(!response.isSuccessful()){

                }else{
                    addGoal=response.body();
                    flag = true;
                }
            }

            @Override
            public void onFailure(Call<userGoalObject> call, Throwable t) {

            }
        });
        return flag;
    }

    public boolean addCustomGoal(customGoalObject addCustomGoal){
         flag =false;
        addingCustomGoal = new customGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<returnGoalObject> customGoalObjectCall = service.addingCustomGoal(addCustomGoal);
        customGoalObjectCall.enqueue(new Callback<returnGoalObject>() {
            @Override
            public void onResponse(Call<returnGoalObject> call, Response<returnGoalObject> response) {
                if(!response.isSuccessful()){

                }else{
                    goalsList=response.body();
                    flag=true;
                }
            }

            @Override
            public void onFailure(Call<returnGoalObject> call, Throwable t) {

            }
        });
        return flag;
    }
}

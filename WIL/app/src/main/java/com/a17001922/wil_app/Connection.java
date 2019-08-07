package com.a17001922.wil_app;

import com.a17001922.wil_app.LoginScreen.LoginUserObject;
import com.a17001922.wil_app.LoginScreen.RegisterUserObject;
import com.a17001922.wil_app.LoginScreen.ReturnedMessage;
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
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://105.184.157.179:44317/").build();
    loginRegisterService service = retrofit.create(loginRegisterService.class);
    private boolean loginAuth=false;
    private boolean registerAuth=false;
    private DailyObject Quote;

    public boolean userLogin(LoginUserObject user){

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

    public boolean userRegister(RegisterUserObject user){

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

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

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
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
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://192.168.43.178:45456/").addConverterFactory(GsonConverterFactory.create())
            .client(getUnsafeOkHttpClient().build()).build();
    loginRegisterService loginRegisterService = retrofit.create(loginRegisterService.class);
    private boolean loginAuth=false;
    private boolean registerAuth=false;
    private DailyObject Quote;
    private returnGoalObject goalsList;
    private userGoalObject addGoal;
    private customGoalObject addingCustomGoal;
    private boolean flag;
    private static final String TAG = "ConnectionClass";




    //________Register New User__________

    public boolean userRegister(RegisterUserObject user)
    {

        final Call<ReturnMessageObject>registerUserCall = loginRegisterService.userRegister(user);
        registerUserCall.enqueue(new Callback<ReturnMessageObject>()
        {
            @Override
            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
            {
                if (!response.isSuccessful())
                {
                    registerAuth=false;
                }
                ReturnMessageObject registeredAuth = response.body();
                if (registeredAuth.getResult())
                {
                    registerAuth=true;
                }
                else
                {
                    registerAuth=false;
                }

            }

            @Override
            public void onFailure(Call<ReturnMessageObject> call, Throwable t)
            {
                registerAuth=false;
            }
        });

        return registerAuth;
    }


    //________Login__________

    public boolean userLogin(LoginUserObject user)
    {

        final Call<ReturnMessageObject>loginUserCall = loginRegisterService.userLogin(user);
        loginUserCall.enqueue(new Callback<ReturnMessageObject>()
        {
            @Override
            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
            {
                if (!response.isSuccessful())
                {
                    loginAuth=false;
                }
                ReturnMessageObject loggedInAuth = response.body();
                if (loggedInAuth.getResult())
                {
                    loginAuth=true;
                }
                else
                {
                    loginAuth=false;
                }

            }

            @Override
            public void onFailure(Call<ReturnMessageObject> call, Throwable t)
            {
                loginAuth=false;
            }
        });

        return loginAuth;
    }





    //________Get Daily Quote__________

    public DailyObject getDailyQuote()
    {
          Quote = new DailyObject();
        DailyQuoteService service = retrofit.create(DailyQuoteService.class);
        final Call<DailyObject> quoteCall= service.getQuote();
        quoteCall.enqueue(new Callback<DailyObject>()
        {
            @Override
            public void onResponse(Call<DailyObject> call, Response<DailyObject> response)
            {
                if(!response.isSuccessful())
                {

                }
                else
                {
                    Quote=response.body();
                }
            }

            @Override
            public void onFailure(Call<DailyObject> call, Throwable t)
            {

            }
        });

        return Quote;
    }


    //________Get Goals__________

    public returnGoalObject getGoals(userGoalObject userGoals)
    {
        goalsList = new returnGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<returnGoalObject> goalsCall= service.getGoalsList(userGoals);
        goalsCall.enqueue(new Callback<returnGoalObject>()
        {
            @Override
            public void onResponse(Call<returnGoalObject> call, Response<returnGoalObject> response)
            {
                if(!response.isSuccessful())
                {

                }
                else
                {
                    goalsList=response.body();
                }
            }

            @Override
            public void onFailure(Call<returnGoalObject> call, Throwable t)
            {

            }
        });

        return goalsList;
    }


    //________Add User Goal__________

    public boolean addUserGoal(userGoalObject addingGoal)
    {
         flag = false;
        addGoal = new userGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<userGoalObject> addGoalCall = service.addingGoal(addingGoal);
        addGoalCall.enqueue(new Callback<userGoalObject>()
        {
            @Override
            public void onResponse(Call<userGoalObject> call, Response<userGoalObject> response)
            {
                if(!response.isSuccessful())
                {

                }
                else
                {
                    addGoal=response.body();
                    flag = true;
                }
            }

            @Override
            public void onFailure(Call<userGoalObject> call, Throwable t)
            {

            }
        });

        return flag;
    }


    //________Add Custom Goal__________

    public boolean addCustomGoal(customGoalObject addCustomGoal)
    {
         flag =false;
        addingCustomGoal = new customGoalObject();
        goalsService service = retrofit.create(goalsService.class);
        final Call<returnGoalObject> customGoalObjectCall = service.addingCustomGoal(addCustomGoal);
        customGoalObjectCall.enqueue(new Callback<returnGoalObject>()
        {
            @Override
            public void onResponse(Call<returnGoalObject> call, Response<returnGoalObject> response)
            {
                if(!response.isSuccessful())
                {

                }
                else
                {
                    goalsList=response.body();
                    flag=true;
                }
            }

            @Override
            public void onFailure(Call<returnGoalObject> call, Throwable t)
            {

            }
        });

        return flag;
    }


    //________Method to Bypass SSL Certificate Error__________

    public static OkHttpClient.Builder getUnsafeOkHttpClient()
    {

        try
        {
            // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]
                {
                        new X509TrustManager()
                        {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
                            {

                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException
                            {

                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers()
                            {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return builder;

        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }

    }



}

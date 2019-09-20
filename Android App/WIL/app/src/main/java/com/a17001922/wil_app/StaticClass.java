package com.a17001922.wil_app;

import android.content.Context;
import android.widget.Toast;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StaticClass
{

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LOGGED_IN_USER = "Logged in user";
    public static final String LOGGED_IN_USER_EMAIL = "Logged in user email";
    public static final String LOGGED_IN_TYPE = "Logged in type";
    public static final String USER_GOALIDS = "user_goal_IDS";
    public static final String USER_GOALNAMES = "user_goal_NAMES";
    public static final String USER_GOALDESCRIPTIONS = "user_goal_DESCRIPTIONS";
    public static final String USER_GOALTYPE = "user_goal_TYPES";
    public static final String USER_GOALCOMPLETED = "user_goal_COMPLETED";
    public static final String USER_LIFESKILLSIDS = "user_LIFESKILLS_IDS";
    public static final String USER_LIFESKILLSNAMES = "user_LIFESKILLS_NAMES";
    public static final String USER_LIFESKILLSCOMPLETED = "user_LIFESKILLS_COMPLETED";

    public static boolean hasInternet = false;
    public static boolean ongoingOperation = false;

    public static Context homeContext;
    public static Context loginContext;


    public static String currentUser = "No_User";

<<<<<<< HEAD
    public static Retrofit retrofit = new Retrofit.Builder().baseUrl("https://192.168.43.178:45457/").addConverterFactory(GsonConverterFactory.create())
=======
    public static Retrofit retrofit = new Retrofit.Builder().baseUrl("https://192.168.43.178:45456/").addConverterFactory(GsonConverterFactory.create())
>>>>>>> 60b1129e6204b34ad62628cd4a985299d2b96359
            .client(getUnsafeOkHttpClient().build()).build();


    //________Method to Bypass SSL Certificate Error__________

    public static OkHttpClient.Builder getUnsafeOkHttpClient()
    {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]
                    {
                            new X509TrustManager() {
                                @Override
                                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                                }

                                @Override
                                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {

                                }

                                @Override
                                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
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

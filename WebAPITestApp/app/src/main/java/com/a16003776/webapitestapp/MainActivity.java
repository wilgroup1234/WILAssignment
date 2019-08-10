package com.a16003776.webapitestapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

public class MainActivity extends AppCompatActivity
{

    private TextView DisplayDailyQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayDailyQuote = findViewById(R.id.txtDailyQuote);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://192.168.43.178:45456/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getUnsafeOkHttpClient().build())
                .build();

        WebAPI webAPI = retrofit.create(WebAPI.class);

        Call<DailyQuote> call = webAPI.getDailyQuote();

        call.enqueue(new Callback<DailyQuote>() {
            @Override
            public void onResponse(Call<DailyQuote> call, Response<DailyQuote> response)
            {
                if (!response.isSuccessful())
                {
                    DisplayDailyQuote.setText("Code: " + response.code());
                    return;
                }

                DailyQuote dailyQuote = response.body();

                DisplayDailyQuote.setText("Quote: " + dailyQuote.getQuoteText() + "\n" + "YoutubeLink: " + dailyQuote.getYoutubeLink());

            }

            @Override
            public void onFailure(Call<DailyQuote> call, Throwable t)
            {
                DisplayDailyQuote.setText(t.getMessage());
            }
        });


    }



    public static OkHttpClient.Builder getUnsafeOkHttpClient()
    {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}

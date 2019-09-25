package com.a17001922.wil_logins;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.mainLogin;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Start extends AppCompatActivity
{
    //Declaration
    private int splashLength = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final int PERMISSIONS_ALL = 10;
        String Permissions [] = {Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE};

        //______Check and ask for Permissions________

        if(!hasPermissions(this, Permissions))
        {
            Toast.makeText(getApplicationContext(), "This app needs to store data on your device for offline use.", Toast.LENGTH_LONG);
            ActivityCompat.requestPermissions(this, Permissions, PERMISSIONS_ALL);
        }

        //Check if device has an internet connection
        Thread thread = new Thread
                (new Runnable()
                {
                    @Override
                    public void run()
                    {
                        if (hasActiveInternetConnection())
                        {
                            StaticClass.hasInternet = true;
                            Log.e("INTERNET: : ", "Internet connection detected");
                        }
                        else
                        {
                            StaticClass.hasInternet = false;
                            Log.e("NO INTERNET: : ", "NO Internet connection detected");

                        }
                    }
                });

        thread.start();



        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                ChangeForm();
            }
        }, splashLength);


    }



    public void ChangeForm()
    {
        //Open Login activity
        Intent intent = new Intent(getApplicationContext(), mainLogin.class);
        startActivity(intent);
    }


    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }

    //_______The following 2 methods check if a user has an internet connection_______

    public boolean hasActiveInternetConnection()
    {

        if (isNetworkAvailable())
        {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("http://www.google.com").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(1500);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            }
            catch (IOException e)
            {
                Log.e("NO INTERNET ERROR: ", "Error checking internet connection", e);
            }
        }
        else
        {
            Log.d("NO INTERNET ERROR: ", "No network available!");
        }
        return false;
    }


    //This method checks if the user has an active internet connection
    private boolean isNetworkAvailable()
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    //_____________________________________________END_____________________________________________



    //______Method to ask user for app permissions__________
    public static boolean hasPermissions(Context context, String [] permissions)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context!=null && permissions != null)
        {
            for (String permission : permissions)
            {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED)
                {
                    return false;
                }
            }
        }

        return true;
    }


}

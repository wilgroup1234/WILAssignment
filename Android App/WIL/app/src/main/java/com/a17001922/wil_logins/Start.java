package com.a17001922.wil_logins;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.a17001922.wil_app.LoginScreen.mainLogin;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

//This class manages the Start/Splash Screen
public class Start extends AppCompatActivity
{
    //Declarations
    private int splashLength = 1500;
    private final String TAG = "SplashScreen";
    SharedPreferences sharedPreferences;
    Context context;
    final int PERMISSIONS_ALL = 10;
    String Permissions [] = {Manifest.permission.INTERNET, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE};

    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        //Declarations
        context = getApplicationContext();
        FirebaseMessaging.getInstance().subscribeToTopic("updates");


        try
        {
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

        }
        catch(Exception e)
        {
            Log.e("Start Exception: ", e.getMessage());
        }


        try
        {

            new Handler().postDelayed(new Runnable()
            {
                //Run splash screen for 1.5 seconds
                @Override
                public void run()
                {
                    showEula();
                }
            }, splashLength);

        }
        catch(Exception e)
        {
            Log.e("Start Exception: ", e.getMessage());
        }


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


    //____________________________METHOD FOR CREATING EULA (End User License Agreement)_______________________________________
    private void showEula()
    {

        String msg = "Do you agree to Goal Pro's Privacy Policy?";
        String msg2 = "http://www.goalpro.co.za/privacy/privacypolicy.html";
        final SpannableString s = new SpannableString(msg2);
        Linkify.addLinks(s, Linkify.ALL);


        Log.e(TAG,"ENTERED EULA");
        sharedPreferences = context.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        Log.e(TAG,"got shared preferences");
        boolean agreed = sharedPreferences.getBoolean("agreed",false);

        Log.e(TAG,"GOT THE EULA VARIABLE");

        if(!agreed)
        {
            Log.e(TAG,"ENTERED IF STATEMENT");
            final AlertDialog alert = new AlertDialog.Builder(Start.this)
                    .setTitle("Do you agree to the terms of policy below and app permissions:")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {

                            //______Check and ask for Permissions________

                            if(!hasPermissions(context, Permissions))
                            {
                                Toast.makeText(context, "This app needs to store data on your device for offline use.", Toast.LENGTH_SHORT);
                                ActivityCompat.requestPermissions(Start.this, Permissions, PERMISSIONS_ALL);
                            }


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("agreed", true);
                            editor.commit();

                            //______Check and ask for Permissions________

                            if(!hasPermissions(context, Permissions))
                            {
                                editor = sharedPreferences.edit();
                                editor.putBoolean("agreed", false);
                                editor.commit();
                                Toast.makeText(Start.this, "Please Accept the above terms to continue...", Toast.LENGTH_SHORT).show();
                                showEula();
                            }
                            else
                            {
                                ChangeForm();
                            }



                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            Toast.makeText(Start.this, "Please Accept the above terms to continue...", Toast.LENGTH_SHORT).show();
                            showEula();
                        }
                    })
                    .setMessage(s).create();
            alert.show();

            ((TextView)alert.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());
        }
        else
        {
            ChangeForm();
        }
    }


}

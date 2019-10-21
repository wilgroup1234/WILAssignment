package com.a17001922.wil_app.LoginScreen;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

//This class manages the creation of the Login and Register Fragments
public class mainLogin extends AppCompatActivity
{

    //_____________Declarations_________________
    private LoginTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG ="loginActivity";

    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        //Declarations
        Context thisContext = getApplicationContext();
        StaticClass.loginContext = thisContext;

    }

    //____________________OnStart Method_____________
    protected void onStart()
    {
     super.onStart();

        //_____________Binding fields and widgets_____________
        viewPager = (ViewPager) findViewById(R.id.loginpager);
        tabLayout = (TabLayout) findViewById(R.id.LtabLayout);
        adapter = new LoginTabAdapter(getSupportFragmentManager());

        //Displays whether app is online or not
        if (StaticClass.hasInternet)
        {
            Toast.makeText(getApplicationContext(), "Working Online", Toast.LENGTH_LONG).show();
        }


            //Adds fragments to screen
            try
            {
                adapter.addFragment(new LoginFragment(),"LOGIN");
                adapter.addFragment(new RegisterFragments(),"REGISTER");
                viewPager.setAdapter(adapter);
                tabLayout.setupWithViewPager(viewPager);
            }
            catch (Exception e)
            {
                Log.e(TAG,"here is the error :",e);
            }

    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


}

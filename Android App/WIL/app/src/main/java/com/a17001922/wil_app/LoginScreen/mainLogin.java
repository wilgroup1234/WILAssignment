package com.a17001922.wil_app.LoginScreen;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;

public class mainLogin extends AppCompatActivity
{
    private LoginTabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private static final String TAG ="loginActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);


    }

    protected void onStart()
    {
     super.onStart();

        if (StaticClass.hasInternet)
        {
            Toast.makeText(getApplicationContext(), "Working Online", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Offline, Cannot Login", Toast.LENGTH_LONG).show();
        }

        viewPager = (ViewPager) findViewById(R.id.loginpager);
        tabLayout = (TabLayout) findViewById(R.id.LtabLayout);
        adapter = new LoginTabAdapter(getSupportFragmentManager());

        try{
            adapter.addFragment(new LoginFragment(),"LOGIN");
            Log.e(TAG,"we added login");
            adapter.addFragment(new RegisterFragments(),"REGISTER");
            Log.e(TAG,"Added register");
            viewPager.setAdapter(adapter);
            Log.e(TAG,"set the adapter");
            tabLayout.setupWithViewPager(viewPager);
            Log.e(TAG,"Added tablayout correctly");
        }catch (Exception e){
            Log.e(TAG,"here is the error :",e);
        }

    }


    public void OpenHomePage(Intent intent)
    {

        startActivity(intent);

    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


}

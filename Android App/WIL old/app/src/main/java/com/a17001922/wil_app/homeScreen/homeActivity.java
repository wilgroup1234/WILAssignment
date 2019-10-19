package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.content.Intent;

import com.a17001922.wil_app.LoginScreen.mainLogin;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class homeActivity extends AppCompatActivity
{
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Toolbar homeToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_home);

        Context thisContext = getApplicationContext();
        StaticClass.homeContext = thisContext;

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.HtabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        homeToolbar = findViewById(R.id.hometoolbar);

        if (StaticClass.hasInternet)
        {
            adapter.addFragment(new goalsFragment(), "Home");
            adapter.addFragment(new viewGoalsFragment(),"View Goals");
            adapter.addFragment(new viewLifeSkillsFragment(),"View LifeSkills");
            adapter.addFragment(new dailyQuoteFragment(), "Daily Quote");
            adapter.addFragment(new streakFragment(),"Streak");
            adapter.addFragment(new GameScreen(),"Game");
            adapter.addFragment(new gratitudeFragment(),"Gratitude Page");
            adapter.addFragment(new stepTrackerFragment(),"Step Tracker");
            adapter.addFragment(new PlannerFragment(),"Planner");
            adapter.addFragment(new uploadDocsFragment(),"Upload");
            adapter.addFragment(new cvUpload(),"CV");
            adapter.addFragment(new socialMediaFragment(),"Social Media");


            Toast.makeText(getApplicationContext(), "Working Online", Toast.LENGTH_LONG).show();
        }
        else
        {
            adapter.addFragment(new viewGoalsFragment(),"View Goals");
            adapter.addFragment(new viewLifeSkillsFragment(),"View LifeSkills");
            adapter.addFragment(new dailyQuoteFragment(), "Daily Quote");
            adapter.addFragment(new GameScreen(),"Game");
            adapter.addFragment(new PlannerFragment(),"Planner");
            adapter.addFragment(new cvUpload(),"CV");
            adapter.addFragment(new uploadDocsFragment(),"Upload");
            adapter.addFragment(new socialMediaFragment(),"Social Media");
            Toast.makeText(getApplicationContext(), "Offline, Limited functionality available...", Toast.LENGTH_LONG).show();
        }


        homeToolbar.inflateMenu(R.menu.menu_items);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


        homeToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener()
        {
            @Override
            public boolean onMenuItemClick(MenuItem item)
            {
                if(item.getTitle().equals("Help")){
                    if(StaticClass.hasInternet){
                        Intent i = new Intent(getApplicationContext(),onlineHelpActivity.class);
                        startActivity(i);
                    }
                    else
                        {
                        Intent i = new Intent(getApplicationContext(),offlineHelpActivity.class);
                        startActivity(i);
                    }
                }
                else if(item.getTitle().equals("Logout"))
                {
                    ResetUser();
                }
                return Boolean.parseBoolean(null);
            }
        });

    }


    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


    public void ResetUser()
    {
        //Set logged in user to false;
        SharedPreferences sharedPreferences = StaticClass.homeContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        String type = sharedPreferences.getString(StaticClass.LOGGED_IN_TYPE, "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, false);
        editor.commit();

        if (type.equals("google"))
        {
            try
            {
                GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                GoogleSignInClient gSignInClient = GoogleSignIn.getClient(StaticClass.homeContext, googleSignInOptions);

                gSignInClient.signOut();
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        Toast.makeText(StaticClass.homeContext, "Signed Out...", Toast.LENGTH_LONG).show();

        OpenLogin();
    }


    public void OpenLogin()
    {
        //Open Login activity
        Intent intent = new Intent(StaticClass.homeContext, mainLogin.class);
        StaticClass.currentUser = "No_User";
        startActivity(intent);
    }

}


package com.a17001922.wil_app.homeScreen;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;
import android.content.Intent;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

public class homeActivity extends AppCompatActivity
{
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.HtabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new goalsFragment(), "Goals");
        adapter.addFragment(new dailyQuoteFragment(), "Daily Quote");
        adapter.addFragment(new streakFragment(),"Streak");
        adapter.addFragment(new gratitudeFragment(),"Gratitude Page");
        adapter.addFragment(new PlannerFragment(),"Planner");
        adapter.addFragment(new uploadDocsFragment(),"Upload");
        adapter.addFragment(new cvUpload(),"CV");
        adapter.addFragment(new stepTrackerFragment(),"Step Tracker");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        if (StaticClass.hasInternet)
        {
            Toast.makeText(getApplicationContext(), "Working Online", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Offline, Limited functionality available...", Toast.LENGTH_LONG).show();
        }
    }


    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


}


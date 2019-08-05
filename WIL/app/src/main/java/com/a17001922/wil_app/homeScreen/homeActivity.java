package com.a17001922.wil_app.homeScreen;

import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.TabLayoutOnPageChangeListener;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.a17001922.wil_app.R;

public class homeActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.pager);
        tabLayout = (TabLayout) findViewById(R.id.HtabLayout);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new goalsFragment(), "Daily Quote");
        adapter.addFragment(new dailyQuoteFragment(), "Goals");
        adapter.addFragment(new PlannerFragment(),"Planner");
        adapter.addFragment(new uploadDocsFragment(),"Upload");
        adapter.addFragment(new cvUpload(),"CV");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}


package com.a17001922.wil_app.LoginScreen;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;

//This is an adapter class used to make the fragments work
public class LoginTabAdapter extends FragmentStatePagerAdapter
{
    //_____________Declarations_________________
    private final List<Fragment> lFragmentList = new ArrayList<>();
    private final List<String> lFragmentTitleList = new ArrayList<>();

    //sets fragment manager
    public LoginTabAdapter(FragmentManager fm) {
        super(fm);
    }

    //Gets selected fragment position and displays the appropriate screen
    @Override
    public Fragment getItem(int position)
    {

        return lFragmentList.get(position);
    }

    //allows for fragments to be added
    public void addFragment(Fragment fragment, String title)
    {
        lFragmentList.add(fragment);
        lFragmentTitleList.add(title);
    }

    //returns title of selected fragment
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lFragmentTitleList.get(position);
    }

    //returns number of fragments
    @Override
    public int getCount() {
        return lFragmentList.size();
    }
}

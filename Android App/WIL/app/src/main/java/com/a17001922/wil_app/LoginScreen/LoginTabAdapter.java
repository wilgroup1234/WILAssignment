package com.a17001922.wil_app.LoginScreen;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import java.util.ArrayList;
import java.util.List;
public class LoginTabAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> lFragmentList = new ArrayList<>();
    private final List<String> lFragmentTitleList = new ArrayList<>();

    public LoginTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return lFragmentList.get(position);
    }
    public void addFragment(Fragment fragment, String title) {
        lFragmentList.add(fragment);
        lFragmentTitleList.add(title);
    }
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lFragmentTitleList.get(position);
    }
    @Override
    public int getCount() {
        return lFragmentList.size();
    }
}

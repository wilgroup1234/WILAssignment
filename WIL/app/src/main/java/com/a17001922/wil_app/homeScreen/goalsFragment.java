package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.goals.userGoalObject;


public class goalsFragment extends Fragment {
    private ListView lvGoals;
    Connection con;
    userGoalObject user = new userGoalObject();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goals, container, false);

        return v;
    }
}

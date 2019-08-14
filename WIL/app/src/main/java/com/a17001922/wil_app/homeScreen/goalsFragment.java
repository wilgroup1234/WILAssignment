package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.goals.goals;
import com.a17001922.wil_app.goals.returnGoalObject;
import com.a17001922.wil_app.goals.userGoalObject;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class goalsFragment extends Fragment {
     private ListView lvGoals;
     Connection con;
     userGoalObject user = new userGoalObject();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_goals,container,false);
      
        return v;
    }
}

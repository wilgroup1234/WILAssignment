package com.a17001922.wil_app.homeScreen;

import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.ReturnGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.userGoalObject;
import com.a17001922.wil_app.goalsRecyclerView.CityAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class viewGoalsFragment extends Fragment {
    private final String TAG="View Goals Page";
    ReturnGoalObject goalsList =new ReturnGoalObject();
    userGoalObject userGoals = new userGoalObject();
    ArrayList<Goal> goalsArrayList= new ArrayList<>();
    boolean flag = false;
    RecyclerView.Adapter adapter;
    private RecyclerView goalsRecycler;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_goals, container, false);
        goalsService service = StaticClass.retrofit.create(goalsService.class);

        userGoals.setEmail(StaticClass.currentUser);
        try {
            final Call<ReturnGoalObject> goalsCall = service.getGoalsList(userGoals);
            goalsCall.enqueue(new Callback<ReturnGoalObject>() {
                @Override

                public void onResponse(Call<ReturnGoalObject> call, Response<ReturnGoalObject> response) {
                    Log.e(TAG,"onResponse: made it into onResponse"+response);
                    if (!response.isSuccessful()) {
                        Log.e(TAG,"Failed to get user goals ");
                    } else {
                        goalsList = response.body();
                        goalsArrayList = (ArrayList<Goal>) goalsList.getGoalList();
                        goalsRecycler = v.findViewById(R.id.listViewGoals);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                        goalsRecycler.setLayoutManager(layoutManager);
                        adapter = new CityAdapter(goalsArrayList);
                        goalsRecycler.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<ReturnGoalObject> call, Throwable t) {
                    Log.e(TAG, "onFailure: failed to populate user goals" );
                }
            });



        }catch(Exception e) {
            Log.e(TAG, "onCreateView: Error that occured",e );
        }

        return v;
    }
}

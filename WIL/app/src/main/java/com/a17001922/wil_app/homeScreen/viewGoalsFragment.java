package com.a17001922.wil_app.homeScreen;

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
import com.a17001922.wil_app.goals.goals;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.returnGoalObject;
import com.a17001922.wil_app.goals.userGoalObject;
import com.a17001922.wil_app.goalsRecyclerView.CityAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class viewGoalsFragment extends Fragment {
    private final String TAG="View Goals Page";
    returnGoalObject goalsList =new returnGoalObject();
    userGoalObject userGoals = new userGoalObject();
    ArrayList<goals> goalsArrayList= new ArrayList<>();
    boolean flag = false;
    RecyclerView.Adapter adapter;
    private RecyclerView goalsRecycler;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_goals, container, false);
        goalsService service = StaticClass.retrofit.create(goalsService.class);

        userGoals.setEmail(StaticClass.currentUser);
        try {
            final Call<returnGoalObject> goalsCall = service.getGoalsList(userGoals);
            goalsCall.enqueue(new Callback<returnGoalObject>() {
                @Override
                public void onResponse(Call<returnGoalObject> call, Response<returnGoalObject> response) {
                    if (!response.isSuccessful()) {

                    } else {
                        goalsList = response.body();
                        goalsArrayList = (ArrayList<goals>) goalsList.getGoalList();
                        flag = true;
                    }
                }

                @Override
                public void onFailure(Call<returnGoalObject> call, Throwable t) {

                }
            });

            if (flag){
                goalsRecycler = v.findViewById(R.id.listViewGoals);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                goalsRecycler.setLayoutManager(layoutManager);
                adapter = new CityAdapter(goalsArrayList);
                goalsRecycler.setAdapter(adapter);
            }
        }catch(Exception e) {
            Log.e(TAG, "onCreateView: Error that occured",e );
        }

        return v;
    }
}

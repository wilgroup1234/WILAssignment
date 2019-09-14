package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.ReturnAllGoalObject;
import com.a17001922.wil_app.goals.ReturnAnyTypeGoalObject;
import com.a17001922.wil_app.goals.ReturnGoalObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import com.a17001922.wil_app.goals.goalsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class viewGoalsFragment extends Fragment
{
    View v;
    Button btnSaveGoalChanges;

    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    RecyclerView viewGoalsRecyclerView;

    private final String TAG = "View Goals Page";

    private final static int tickImage = R.drawable.greentick;
    private final static int exclamationImage = R.drawable.exclamationmark;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<ReturnAnyTypeGoalObject> allListedGoals;

    ArrayList<cardViewItem> cardList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_view_goals, container, false);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        btnSaveGoalChanges = v.findViewById(R.id.btnSaveGoalChanges);
        viewGoalsRecyclerView = v.findViewById(R.id.viewGoalsRecyclerView);
        viewGoalsRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());



        cardList.add(new cardViewItem(tickImage,"No goals added yet", "-", true));



        UserGoalObject userGoalObject = new UserGoalObject();
        userGoalObject.setEmail(StaticClass.currentUser);

        final Call<ReturnAllGoalObject> getGoals = service.getGoalsList(userGoalObject);

        try
        {
            getGoals.enqueue(new Callback<ReturnAllGoalObject>()
            {
                @Override
                public void onResponse(Call<ReturnAllGoalObject> call, Response<ReturnAllGoalObject> response)
                {
                    if (response.isSuccessful())
                    {
                        ReturnAllGoalObject returnGoalObject = response.body();

                        allListedGoals = returnGoalObject.getGoalList();
                        Log.e(TAG, " user goals retrieved successfully");

                        if (allListedGoals.size() > 0)
                        {
                            cardList = new ArrayList<>();

                            for(ReturnAnyTypeGoalObject goal: allListedGoals)
                            {
                                Log.e(TAG, "Goal: " + goal.getGoalName() + " " +  goal.getGoalDescription());

                                if (goal.getCompleted() > 0)
                                {
                                    cardList.add(new cardViewItem(tickImage, goal.getGoalName(), goal.getGoalDescription(), true));
                                }
                                else
                                {
                                    cardList.add(new cardViewItem(exclamationImage, goal.getGoalName(), goal.getGoalDescription(), false));
                                }

                            }

                            recyclerViewAdapter = new ViewGoalsAdapter(cardList);
                            viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);
                        }
                        else
                        {
                            recyclerViewAdapter = new ViewGoalsAdapter(cardList);
                            viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);
                        }


                    }
                    else
                    {
                        recyclerViewAdapter = new ViewGoalsAdapter(cardList);
                        viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                        viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);

                        Log.e(TAG, "ERROR retrieving user goals successfully");
                    }
                }

                @Override
                public void onFailure(Call<ReturnAllGoalObject> call, Throwable t)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG);
                    Log.e(TAG, " OnFailure error: can't connect");
                }
            });

        }
        catch(Exception e)
        {

        }


        btnSaveGoalChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


    }
}

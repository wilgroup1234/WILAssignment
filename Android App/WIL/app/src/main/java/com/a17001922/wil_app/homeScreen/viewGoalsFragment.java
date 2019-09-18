package com.a17001922.wil_app.homeScreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.ReturnAllGoalObject;
import com.a17001922.wil_app.goals.ReturnAnyTypeGoalObject;
import com.a17001922.wil_app.goals.ReturnGoalObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import com.google.api.client.util.DateTime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class viewGoalsFragment extends Fragment
{
    View v;
    Button btnSaveGoalChanges;

    private ViewGoalsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;

    RecyclerView viewGoalsRecyclerView;

    private final String TAG = "View Goals Page";

    private final static int tickImage = R.drawable.greentick;
    private final static int exclamationImage = R.drawable.exclamationmark;
    private final static int crossImage = R.drawable.redx;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<ReturnAnyTypeGoalObject> allListedGoals;
    ArrayList<GoalsCheckedClass> originalGoalList = new ArrayList<>();
    ArrayList<GoalsCheckedClass> changedGoalList = new ArrayList<>();
    ArrayList<GoalsCheckedClass> updatedChangedGoalList = new ArrayList<>();
    int itemCount = 0;


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


        GetAndDisplayUserGoals();


        btnSaveGoalChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                String gNameList = sharedPreferences.getString(StaticClass.USER_GOALNAMES, "");
                String gIDList = sharedPreferences.getString(StaticClass.USER_GOALIDS, "");
                String gCompList = sharedPreferences.getString(StaticClass.USER_GOALCOMPLETED, "");
                String gDescList = sharedPreferences.getString(StaticClass.USER_GOALDESCRIPTIONS, "");
                String gTypeList = sharedPreferences.getString(StaticClass.USER_GOALTYPE, "");

                String [] typesArr = gTypeList.split("#");

                for (int i = 0; i< typesArr.length; i++)
                {
                    Log.e("After Commit ", "type " + typesArr[i]);
                }

                ArrayList<GoalsCheckedClass> originalList = new ArrayList<>();

                int index = 0;

                String[] gNames = gNameList.split("#");
                String[] gIDs = gIDList.split("#");
                String[] gComps = gCompList.split("#");
                String[] gDescs = gDescList.split("#");
                String[] gTypes = gTypeList.split("#");


                for(String val : gNames)
                {
                    String gID = gIDs[index];
                    String gName = gNames[index];
                    String gDesc= gDescs[index];
                    boolean gComp;
                    boolean gType;

                   // int type = Integer.parseInt(gTypes[index].trim());
                  //  Log.e("After Commit ", "int type $" + gTypes[index] + "$");


                    if (gComps[index].equals("1"))
                    {
                        gComp = true;
                    }
                    else
                    {
                        gComp = false;
                    }

                    if (gTypes[index].contains("1"))
                    {
                        gType = false;
                    }
                    else
                    {
                        gType = true;
                    }

                    Log.e(TAG, " SP Goal: " + gID + " isNormal" + gType + " isChecked: " + gComp + "");

                    GoalsCheckedClass newGCC = new GoalsCheckedClass();
                    newGCC.setChecked(gComp);
                    newGCC.setNormalGoal(gType);
                    newGCC.setGoalID(Integer.parseInt(gID));

                    originalList.add(newGCC);

                    index++;
                }


                changedGoalList = recyclerViewAdapter.getChangedGoalList();
                updatedChangedGoalList.clear();


                for (GoalsCheckedClass g : changedGoalList)
                {
                    Log.e(TAG, " Changed GOAL: " + g.getGoalID() + " isNormal" + g.isNormalGoal() + " isChecked: " + g.isChecked());

                    for (GoalsCheckedClass GCC: originalList)
                    {
                        if (g.getGoalID() == GCC.getGoalID() && g.isChecked() != GCC.isChecked())
                        {
                            updatedChangedGoalList.add(g);
                        }
                    }
                }


                for(GoalsCheckedClass GCC: updatedChangedGoalList)
                {
                    Log.e(TAG, " Updated Changed GOAL LIST : " + GCC.getGoalID() + " isNormal" + GCC.isNormalGoal() + " isChecked: " + GCC.isChecked());
                }




                try
                {
                    //changedGoalList = recyclerViewAdapter.getChangedGoalList();
                    itemCount = updatedChangedGoalList.size();

                    for (GoalsCheckedClass goal : updatedChangedGoalList)
                    {
                        itemCount--;
                        UserGoalObject userGoalObject1 = new UserGoalObject();
                        userGoalObject1.setEmail(StaticClass.currentUser);
                        userGoalObject1.setGoalId(goal.getGoalID());

                        if (goal.isNormalGoal())
                        {
                            Log.e(TAG, " IN METHOD");
                            try
                            {
                                final Call<ReturnMessageObject> markOffNormalGoal = service.markOffNormalGoal(userGoalObject1);
                                markOffNormalGoal.enqueue(new Callback<ReturnMessageObject>()
                                {
                                    @Override
                                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                    {
                                        if (response.isSuccessful())
                                        {
                                            ReturnMessageObject returnMessageObject = response.body();

                                            if (returnMessageObject.getResult())
                                            {
                                                Log.e(TAG, " normal goal marked off successfully");
                                            }
                                            else
                                            {
                                                Log.e(TAG, " normal goal mark off unsuccessful :(");
                                            }

                                            if (itemCount == 0)
                                            {
                                                GetAndDisplayUserGoals();
                                            }


                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                    {
                                        Log.e(TAG, " OnFailure error: can't mark off normal goal");
                                    }
                                });

                            }
                            catch(Exception e)
                            {
                                Log.e(TAG, " Exception error: " + e.getMessage());
                            }
                        }
                        else
                        {
                            try
                            {
                                final Call<ReturnMessageObject> markOffCustomGoal = service.markOffCustomGoal(userGoalObject1);
                                markOffCustomGoal.enqueue(new Callback<ReturnMessageObject>()
                                {
                                    @Override
                                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                    {
                                        if (response.isSuccessful())
                                        {
                                            ReturnMessageObject returnMessageObject = response.body();

                                            if (returnMessageObject.getResult())
                                            {
                                                Log.e(TAG, " custom goal marked off successfully");
                                            }
                                            else
                                            {
                                                Log.e(TAG, " custom goal mark off unsuccessful :(");
                                            }

                                            if (itemCount == 0)
                                            {
                                                GetAndDisplayUserGoals();
                                            }

                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                    {
                                        Log.e(TAG, " OnFailure error: can't mark off custom goal");
                                    }
                                });
                            }
                            catch(Exception e)
                            {
                                Log.e(TAG, " Exception error: " + e.getMessage());
                            }
                        }


                    }



                }
                catch(Exception e)
                {
                    Toast.makeText(getActivity().getApplicationContext(), "An error occurred :(",Toast.LENGTH_LONG);
                    Log.e(TAG, " Exception error: " + e.getMessage());
                }








            }
        });


    }

    public void GetAndDisplayUserGoals()
    {

        allListedGoals = new ArrayList<>();
        originalGoalList = new ArrayList<>();
        changedGoalList = new ArrayList<>();
        updatedChangedGoalList = new ArrayList<>();


        cardList = new ArrayList<>();


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

                            try
                            {
                                SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String goalNames = "", goalIds = "", goalDescs = "", goalCompleteds = "", goalTypes = "";

                                int count = 0;

                                for(ReturnAnyTypeGoalObject goal: allListedGoals)
                                {

                                    GoalsCheckedClass goalsCheckedClass = new GoalsCheckedClass();
                                    goalsCheckedClass.setGoalID(goal.getGoalID());
                                    goalsCheckedClass.setNormalGoal(goal.getNormalGoal());

                                    Log.e(TAG, "Goal: " + goal.getGoalName() + " " +  goal.getGoalDescription() + " " + goal.getCompleted() + " is normal: " + goal.getNormalGoal());

                                    if (goal.getCompleted() == 1)
                                    {
                                        Boolean proceed = true;

                                        if (goal.getNormalGoal() == false)
                                        {
                                            Boolean goalExpired = IsGoalExpired(goal.getFinishDate(), goal.getCurrentDate());

                                            if(!goalExpired)
                                            {
                                                proceed = true;
                                            }
                                            else
                                            {
                                                proceed = false;
                                                cardList.add(new cardViewItem(crossImage, goal.getGoalName(), goal.getGoalDescription(), true));
                                                goalsCheckedClass.setChecked(true);
                                            }
                                        }

                                        if (proceed)
                                        {
                                            cardList.add(new cardViewItem(tickImage, goal.getGoalName(), goal.getGoalDescription(), true));
                                            goalsCheckedClass.setChecked(true);
                                        }


                                    }
                                    else
                                    {
                                        Boolean proceed = true;

                                        if (goal.getNormalGoal() == false)
                                        {
                                            Boolean goalExpired = IsGoalExpired(goal.getFinishDate(), goal.getCurrentDate());

                                            if(!goalExpired)
                                            {
                                                proceed = true;
                                            }
                                            else
                                            {
                                                proceed = false;
                                                cardList.add(new cardViewItem(crossImage, goal.getGoalName(), goal.getGoalDescription(), false));
                                                goalsCheckedClass.setChecked(false);
                                            }
                                        }

                                        if (proceed)
                                        {
                                            cardList.add(new cardViewItem(exclamationImage, goal.getGoalName(), goal.getGoalDescription(), false));
                                            goalsCheckedClass.setChecked(false);
                                        }


                                    }




                                    originalGoalList.add(goalsCheckedClass);

                                    if(count == 0)
                                    {
                                        goalIds = goal.getGoalID() + "";

                                        if (goal.getCompleted() == 1)
                                        {
                                            goalCompleteds = "1";
                                        }
                                        else
                                        {
                                            goalCompleteds = "0";
                                        }

                                        goalDescs = goal.getGoalDescription();
                                        goalNames = goal.getGoalName();

                                        if (goal.getNormalGoal())
                                        {
                                            goalTypes = "0";
                                        }
                                        else
                                        {
                                            goalTypes = "1";
                                        }

                                    }
                                    else
                                    {
                                        goalIds = goalIds + "#" + goal.getGoalID();

                                        if (goal.getCompleted() == 1)
                                        {
                                            goalCompleteds = goalCompleteds + "#" + "1";
                                        }
                                        else
                                        {
                                            goalCompleteds = goalCompleteds + "#" + "0";
                                        }

                                        goalDescs = goalDescs + "#" + goal.getGoalDescription();
                                        goalNames = goalNames + "#" + goal.getGoalName();

                                        if (goal.getNormalGoal())
                                        {
                                            goalTypes = goalTypes + "#" + "0";
                                        }
                                        else
                                        {
                                            goalTypes = goalTypes + "#" + "1";
                                        }
                                    }


                                    count ++;
                                }


                                editor.putString(StaticClass.USER_GOALIDS, goalIds);
                                editor.putString(StaticClass.USER_GOALCOMPLETED, goalCompleteds);
                                editor.putString(StaticClass.USER_GOALNAMES, goalNames);
                                editor.putString(StaticClass.USER_GOALTYPE, goalTypes);
                                editor.putString(StaticClass.USER_GOALDESCRIPTIONS, goalDescs);

                                editor.commit();
                            }
                            catch(Exception e)
                            {
                                Log.e(TAG, "Exception: " + e.getMessage());
                            }



                            recyclerViewAdapter = new ViewGoalsAdapter(cardList, originalGoalList);
                            viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);
                        }
                        else
                        {
                            recyclerViewAdapter = new ViewGoalsAdapter(cardList, originalGoalList);
                            viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);
                        }


                    }
                    else
                    {
                        recyclerViewAdapter = new ViewGoalsAdapter(cardList, originalGoalList);
                        viewGoalsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                        viewGoalsRecyclerView.setAdapter(recyclerViewAdapter);

                        Log.e(TAG, "ERROR retrieving user goals successfully");
                    }
                }

                @Override
                public void onFailure(Call<ReturnAllGoalObject> call, Throwable t)
                {
                    //Toast.makeText(getActivity().getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG);
                    Log.e(TAG, " OnFailure error: can't connect");
                }
            });

        }
        catch(Exception e)
        {
            //Toast.makeText(getActivity().getApplicationContext(), "An error occurred :(",Toast.LENGTH_LONG);
            Log.e(TAG, " Exception error: " + e.getMessage());
        }
    }



    public Boolean IsGoalExpired(String date, String currentDate)
    {
        Boolean goalExpired = false;

        //Compare Dates
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        try
        {
            date1 = df2.parse(currentDate);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        Log.e(TAG,"Today : "+ date1);

        String getDate = date;

        Date date2 = null;
        try
        {
            date2 = df2.parse(getDate);
        } catch (ParseException e)
        {
            e.printStackTrace();
        }

        Log.e(TAG,"Get Date : "+ date2);

        if (date1.compareTo(date2) < 0)
        {
            goalExpired = true;
        }

        return goalExpired;
    }


}

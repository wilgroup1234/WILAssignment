package com.a17001922.wil_app.homeScreen;

import android.content.SharedPreferences;
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

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.LifeSkillObject;
import com.a17001922.wil_app.goals.ReturnLifeSkillsObject;
import com.a17001922.wil_app.goals.goalsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class viewLifeSkillsFragment extends Fragment
{
    View v;
    Button btnSaveLifeSkillChanges;

    private ViewLifeSkillsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    SharedPreferences sharedPreferences;

    RecyclerView viewLifeSkillsRecyclerView;

    private final String TAG = "View LifeSkills Page";

    private final static int tickImage = R.drawable.greentick;
    private final static int exclamationImage = R.drawable.exclamationmark;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);

    List<LifeSkillObject> allListedLifeSkills;
    int itemCount = 0;

    ArrayList<LifeSkillChecked> originalLifeSkillsList = new ArrayList<>();
    ArrayList<LifeSkillChecked> changedLifeSkillsList = new ArrayList<>();
    ArrayList<LifeSkillChecked> updatedLifeSkillslList = new ArrayList<>();
    ArrayList<cardViewItem2> cardList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_view_life_skills, container, false);

        return v;
    }

    @Override
    public void onStart()
    {
        super.onStart();

        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);

        btnSaveLifeSkillChanges = v.findViewById(R.id.btnSaveLifeSkillChanges);
        viewLifeSkillsRecyclerView = v.findViewById(R.id.viewLifeSkillsRecyclerView);
        viewLifeSkillsRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());


        GetAndDisplayLifeSkills();

        btnSaveLifeSkillChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String lNameList = sharedPreferences.getString(StaticClass.USER_LIFESKILLSNAMES, "");
                String lIDList = sharedPreferences.getString(StaticClass.USER_LIFESKILLSIDS, "");
                String lCompList = sharedPreferences.getString(StaticClass.USER_LIFESKILLSCOMPLETED, "");

                ArrayList<LifeSkillChecked> originalList = new ArrayList<>();

                int index = 0;

                String[] lNames = lNameList.split("#");
                String[] lIDs = lIDList.split("#");
                String[] lComps = lCompList.split("#");


                for(String val : lNames)
                {
                    String lID = lIDs[index];
                    String lName = lNames[index];
                    boolean lComp;


                    if (lComps[index].equals("1"))
                    {
                        lComp = true;
                    }
                    else
                    {
                        lComp = false;
                    }


                    Log.e(TAG, " SP Life SKill: " + lID + " is Completed: " + lComp + "");

                    LifeSkillChecked newLC = new LifeSkillChecked();
                    newLC.setCompleted(lComp);
                    newLC.setLifeSkillID(Integer.parseInt(lID));

                    originalList.add(newLC);

                    index++;
                }


                changedLifeSkillsList = recyclerViewAdapter.getChangedLifeSkillsList();
                updatedLifeSkillslList.clear();


                for (LifeSkillChecked l : changedLifeSkillsList)
                {
                    Log.e(TAG, " Changed Life Skill: " + l.getLifeSkillID() + " isChecked: " + l.isCompleted());

                    for (LifeSkillChecked LC: originalList)
                    {
                        if (l.getLifeSkillID() == LC.getLifeSkillID() && l.isCompleted() != LC.isCompleted())
                        {
                            updatedLifeSkillslList.add(l);
                        }
                    }
                }


                for(LifeSkillChecked LC: updatedLifeSkillslList)
                {
                    Log.e(TAG, " Updated Changed LIFE SKILLS LIST : " +LC.getLifeSkillID() + " isChecked: " + LC.isCompleted());
                }


                //push new life skill

                try
                {
                    itemCount = updatedLifeSkillslList.size();

                    for (LifeSkillChecked lSkill : updatedLifeSkillslList)
                    {
                        itemCount--;
                        LifeSkillObject object = new LifeSkillObject();
                        object.setEmail(StaticClass.currentUser);
                        object.setLifeSkillID(lSkill.getLifeSkillID());

                            try
                            {
                                final Call<ReturnMessageObject> markOffLifeSkill = service.markOffLifeSkill(object);
                                markOffLifeSkill.enqueue(new Callback<ReturnMessageObject>()
                                {
                                    @Override
                                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                    {
                                        if (response.isSuccessful())
                                        {
                                            ReturnMessageObject returnMessageObject = response.body();

                                            if (returnMessageObject.getResult())
                                            {
                                                Log.e(TAG, " life skill marked off successfully");
                                            }
                                            else
                                            {
                                                Log.e(TAG, " life skill mark off unsuccessful :(");
                                            }

                                            if (itemCount == 0)
                                            {
                                                GetAndDisplayLifeSkills();
                                            }


                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                    {
                                        Log.e(TAG, " OnFailure error: can't mark off life skill");
                                    }
                                });

                            }
                            catch(Exception e)
                            {
                                Log.e(TAG, " Exception error: " + e.getMessage());
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





    public void GetAndDisplayLifeSkills()
    {
        allListedLifeSkills= new ArrayList<>();
        originalLifeSkillsList = new ArrayList<>();
        changedLifeSkillsList = new ArrayList<>();
        updatedLifeSkillslList = new ArrayList<>();
        cardList = new ArrayList<>();


        LifeSkillObject lifeSkillObject = new LifeSkillObject();
        lifeSkillObject.setEmail(StaticClass.currentUser);

        final Call<ReturnLifeSkillsObject> getLifeSkills = service.getLifeSkills(lifeSkillObject);

        try
        {
            getLifeSkills.enqueue(new Callback<ReturnLifeSkillsObject>()
            {
                @Override
                public void onResponse(Call<ReturnLifeSkillsObject> call, Response<ReturnLifeSkillsObject> response)
                {
                    if (response.isSuccessful())
                    {
                        ReturnLifeSkillsObject returnObject = response.body();

                        allListedLifeSkills = returnObject.getLifeSkillsList();
                        Log.e(TAG, " user lifeSkills retrieved successfully");

                        if (allListedLifeSkills.size() > 0)
                        {
                            cardList = new ArrayList<>();


                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            String LifeSkillsNames = "", LifeSkillsIds = "", LifeSkillsCompleteds = "";

                            int count = 0;

                            for(LifeSkillObject lSkill: allListedLifeSkills)
                            {

                                LifeSkillChecked lifeSkillChecked = new LifeSkillChecked();
                                lifeSkillChecked.setLifeSkillID(lSkill.getLifeSkillID());

                                Log.e(TAG, "LifeSkill: " + lSkill.getLifeSkillName() + " is completed:" +  lSkill.getCompleted());

                                if (lSkill.getCompleted() == 1)
                                {
                                    cardList.add(new cardViewItem2(tickImage, lSkill.getLifeSkillName(), true));
                                    lifeSkillChecked.setCompleted(true);
                                }
                                else
                                {
                                    cardList.add(new cardViewItem2(exclamationImage, lSkill.getLifeSkillName(), false));
                                    lifeSkillChecked.setCompleted(false);
                                }




                                originalLifeSkillsList.add(lifeSkillChecked);

                                if(count == 0)
                                {
                                    LifeSkillsIds = lSkill.getLifeSkillID() + "";

                                    if (lSkill.getCompleted() == 1)
                                    {
                                        LifeSkillsCompleteds = "1";
                                    }
                                    else
                                    {
                                        LifeSkillsCompleteds = "0";
                                    }

                                    LifeSkillsNames = lSkill.getLifeSkillName();


                                }
                                else
                                {
                                    LifeSkillsIds = LifeSkillsIds+ "#" + lSkill.getLifeSkillID();

                                    if (lSkill.getCompleted() == 1)
                                    {
                                        LifeSkillsCompleteds = LifeSkillsCompleteds + "#" + "1";
                                    }
                                    else
                                    {
                                        LifeSkillsCompleteds = LifeSkillsCompleteds + "#" + "0";
                                    }

                                    LifeSkillsNames  = LifeSkillsNames  + "#" + lSkill.getLifeSkillName();

                                }


                                count ++;
                            }


                            editor.putString(StaticClass.USER_LIFESKILLSIDS, LifeSkillsIds);
                            editor.putString(StaticClass.USER_LIFESKILLSCOMPLETED, LifeSkillsCompleteds);
                            editor.putString(StaticClass.USER_LIFESKILLSNAMES, LifeSkillsNames);
                            editor.commit();

                            recyclerViewAdapter = new ViewLifeSkillsAdapter(cardList, originalLifeSkillsList);
                            viewLifeSkillsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewLifeSkillsRecyclerView.setAdapter(recyclerViewAdapter);
                        }
                        else
                        {
                            recyclerViewAdapter = new ViewLifeSkillsAdapter(cardList, originalLifeSkillsList);
                            viewLifeSkillsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                            viewLifeSkillsRecyclerView.setAdapter(recyclerViewAdapter);
                        }


                    }
                    else
                    {
                        recyclerViewAdapter = new ViewLifeSkillsAdapter(cardList, originalLifeSkillsList);
                        viewLifeSkillsRecyclerView.setLayoutManager(recyclerViewLayoutManager);
                        viewLifeSkillsRecyclerView.setAdapter(recyclerViewAdapter);

                        Log.e(TAG, "ERROR retrieving user LIFE SKILLS successfully");
                    }
                }

                @Override
                public void onFailure(Call<ReturnLifeSkillsObject> call, Throwable t)
                {
                   // Toast.makeText(getActivity().getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG);
                    Log.e(TAG, " OnFailure error: can't connect");
                }
            });

        }
        catch(Exception e)
        {
          //  Toast.makeText(getActivity().getApplicationContext(), "An error occurred :(",Toast.LENGTH_LONG);
            Log.e(TAG, " Exception error: " + e.getMessage());
        }
    }
}

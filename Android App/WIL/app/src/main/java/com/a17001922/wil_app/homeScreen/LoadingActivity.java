package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.dailyQuote.DailyObject;
import com.a17001922.wil_app.dailyQuote.DailyQuoteService;
import com.a17001922.wil_app.goals.LifeSkillObject;
import com.a17001922.wil_app.goals.ReturnAllGoalObject;
import com.a17001922.wil_app.goals.ReturnAnyTypeGoalObject;
import com.a17001922.wil_app.goals.ReturnLifeSkillsObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This class manages the Loading Screen
public class LoadingActivity extends AppCompatActivity
{
    //_____________Declarations_________________
    private static final String TAG = "LoadingActivityActivity";
    Context context;
    LoadingScreen View;
    private final static long TIMER_INTERVAL = 18;
    private Timer timer;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<ReturnAnyTypeGoalObject> allListedGoals;
    List<LifeSkillObject> allListedLifeSkills;
    SharedPreferences sharedPreferences;
    DailyObject Quote;



    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_acivity);

        //_____________Binding fields and widgets_____________
        context = getApplicationContext();
        sharedPreferences = getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);



        if(StaticClass.hasInternet)
        {
            GetGoals();
        }
        else
        {
            StaticClass.loaded = true;
        }

        View = new LoadingScreen(context);

        setContentView(View);


        timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                if (StaticClass.loaded)
                {
                    OpenHomeActivity();
                    timer.cancel();
                }
                else
                {
                    View.invalidate();

                }


            }
        }, 0, TIMER_INTERVAL);


    }


    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }

    //________Open Home Activity________
    public void OpenHomeActivity()
    {
        Intent intent = new Intent(context, homeActivity.class);
        startActivity(intent);
    }

    //Get all user Goals from API
    public void GetGoals()
    {
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
                    allListedGoals = new ArrayList<>();
                    if (response.isSuccessful())
                    {
                        ReturnAllGoalObject returnGoalObject = response.body();

                        allListedGoals = returnGoalObject.getGoalList();
                        Log.e(TAG, " user goals retrieved successfully");

                        if (allListedGoals.size() > 0)
                        {

                            try
                            {

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String goalNames = "", goalIds = "", goalDescs = "", goalCompleteds = "", goalTypes = "", goalDates = "",  goalCurrentDates = "";

                                int count = 0;

                                for(ReturnAnyTypeGoalObject goal: allListedGoals)
                                {

                                    GoalsCheckedClass goalsCheckedClass = new GoalsCheckedClass();
                                    goalsCheckedClass.setGoalID(goal.getGoalID());
                                    goalsCheckedClass.setNormalGoal(goal.getNormalGoal());

                                    Log.e(TAG, "Goal: " + goal.getGoalName() + " " +  goal.getGoalDescription() + " " + goal.getCompleted() + " is normal: " + goal.getNormalGoal());



                                    if(count == 0)
                                    {
                                        if(goal.getNormalGoal())
                                        {
                                            goalCurrentDates = " ";
                                        }
                                        else
                                        {
                                            goalCurrentDates = goal.getCurrentDate();
                                        }

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
                                            goalDates = " ";
                                        }
                                        else
                                        {
                                            goalTypes = "1";
                                            goalDates = goal.getFinishDate();
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
                                            goalDates = goalDates + "#" + " ";
                                            goalCurrentDates = goalCurrentDates + "#" + " ";
                                        }
                                        else
                                        {
                                            goalTypes = goalTypes + "#" + "1";
                                            goalDates = goalDates + "#" + goal.getFinishDate();
                                            goalCurrentDates = goalCurrentDates + "#" + goal.getCurrentDate();
                                        }
                                    }


                                    count ++;
                                }

                                sharedPreferences = getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                                editor.putString(StaticClass.USER_GOALIDS, goalIds);
                                editor.putString(StaticClass.USER_GOALCOMPLETED, goalCompleteds);
                                editor.putString(StaticClass.USER_GOALNAMES, goalNames);
                                editor.putString(StaticClass.USER_GOALTYPE, goalTypes);
                                editor.putString(StaticClass.USER_GOALDESCRIPTIONS, goalDescs);
                                editor.putString(StaticClass.USER_GOALDATES, goalDates);
                                editor.putString(StaticClass.USER_GOALCURRENTDATES, goalCurrentDates);

                                Log.e(TAG, " GOAL IDS AFTER LOADING: " + goalIds);
                                Log.e(TAG, " GOAL Complteds AFTER LOADING: " + goalCompleteds);
                                Log.e(TAG, " GOAL nameS AFTER LOADING: " + goalNames);
                                Log.e(TAG, " GOAL types AFTER LOADING: " + goalTypes);
                                Log.e(TAG, " GOAL descsS AFTER LOADING: " + goalDescs);
                                Log.e(TAG, " GOAL dateS AFTER LOADING: " + goalDates);

                                editor.commit();

                                GetLifeSkills();
                            }
                            catch(Exception e)
                            {
                                Log.e(TAG, "Exception: " + e.getMessage());
                                GetLifeSkills();

                            }
                        }
                        else
                        {
                            GetLifeSkills();
                        }


                    }
                    else
                    {
                        Log.e(TAG, "ERROR retrieving user goals successfully");
                        GetLifeSkills();
                    }
                }

                @Override
                public void onFailure(Call<ReturnAllGoalObject> call, Throwable t)
                {
                    GetLifeSkills();
                }
            });

        }
        catch(Exception e)
        {
            GetLifeSkills();
        }
    }



    //Get all USer Life Skills from API
    public void GetLifeSkills()
    {
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

                        if (allListedLifeSkills.size() > 0)
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            String LifeSkillsNames = "", LifeSkillsIds = "", LifeSkillsCompleteds = "";

                            int count = 0;

                            for(LifeSkillObject lSkill: allListedLifeSkills)
                            {

                                LifeSkillChecked lifeSkillChecked = new LifeSkillChecked();
                                lifeSkillChecked.setLifeSkillID(lSkill.getLifeSkillID());

                                Log.e(TAG, "LifeSkill: " + lSkill.getLifeSkillName() + " is completed:" +  lSkill.getCompleted());


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

                            sharedPreferences = getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                            editor.putString(StaticClass.USER_LIFESKILLSIDS, LifeSkillsIds);
                            editor.putString(StaticClass.USER_LIFESKILLSCOMPLETED, LifeSkillsCompleteds);
                            editor.putString(StaticClass.USER_LIFESKILLSNAMES, LifeSkillsNames);
                            editor.commit();

                            GetDailyQuote();
                        }
                        else
                        {
                            GetDailyQuote();
                        }


                    }
                    else
                    {
                        Log.e(TAG, "ERROR retrieving user LIFE SKILLS successfully");
                        GetDailyQuote();
                    }
                }

                @Override
                public void onFailure(Call<ReturnLifeSkillsObject> call, Throwable t)
                {
                    Log.e(TAG, " OnFailure error: can't connect");
                    GetDailyQuote();
                }
            });

        }
        catch(Exception e)
        {
            Log.e(TAG, " Exception error: " + e.getMessage());
            GetDailyQuote();
        }
    }


    //Get the latest Daily Quote from API
    public void GetDailyQuote()
    {

        try
        {
            Quote = new DailyObject();
            DailyQuoteService service = StaticClass.retrofit.create(DailyQuoteService.class);
            final Call<DailyObject> quoteCall = service.getQuote();
            quoteCall.enqueue(new Callback<DailyObject>()
            {
                @Override
                public void onResponse(Call<DailyObject> call, Response<DailyObject> response)
                {
                    if (!response.isSuccessful())
                    {
                       GetGratitude();
                    }
                    else
                    {
                        Quote = response.body();

                        if(Quote == null)
                        {
                            Toast.makeText(getApplicationContext(), "There was an error retrieving the daily quote", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            if(Quote.getQuoteText() == null)
                            {
                                Toast.makeText(getApplicationContext(), "There is no new daily quote available :(", Toast.LENGTH_SHORT).show();
                                GetGratitude();
                            }
                            else
                            {
                                try
                                {
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    sharedPreferences = getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                                    editor.putString(StaticClass.USER_DAILYQUOTEImage, Quote.getTemplateID() + "");
                                    editor.putString(StaticClass.USER_DAILYQUOTELINK, Quote.getYoutubeLink());
                                    editor.putString(StaticClass.USER_DAILYQUOTETEXT, Quote.getQuoteText());
                                    editor.commit();
                                    GetGratitude();
                                }
                                catch(Exception e)
                                {
                                    Log.e(TAG, "Exception caught: " + e.getMessage());
                                    GetGratitude();
                                }
                            }

                        }
                    }
                }

                @Override
                public void onFailure(Call<DailyObject> call, Throwable t)
                {
                    Toast.makeText(getApplicationContext(), "Response from API failed", Toast.LENGTH_SHORT).show();
                    GetGratitude();
                }
            });
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "UNABLE TO GET DAILY QUOTE", Toast.LENGTH_LONG).show();
        }
    }


    //Get User Gratitude Items from API
    public void GetGratitude()
    {
        GratitudeObject gratitudeObject = new GratitudeObject();
        gratitudeObject.setEmail(StaticClass.currentUser);

        try
        {
            final Call<GratitudeObject> gratitudeCall = service.getUserGratitude(gratitudeObject);
            gratitudeCall.enqueue(new Callback<GratitudeObject>()
            {
                @Override
                public void onResponse(Call<GratitudeObject> call, Response<GratitudeObject> response)
                {
                    try
                    {
                        String[] items;

                        GratitudeObject returnGratitude = response.body();
                        String gratItems = returnGratitude.getItems();

                        try
                        {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            sharedPreferences = getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                            editor.putString(StaticClass.USER_Grats, gratItems);
                            editor.commit();
                        }
                        catch(Exception e)
                        {

                        }

                        StaticClass.loaded = true;
                    }
                    catch(Exception e)
                    {
                        StaticClass.loaded = true;
                    }

                }

                @Override
                public void onFailure(Call<GratitudeObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user gratitude");
                    StaticClass.loaded = true;
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving gratitude: " + e.toString());
            StaticClass.loaded = true;
        }
    }


}
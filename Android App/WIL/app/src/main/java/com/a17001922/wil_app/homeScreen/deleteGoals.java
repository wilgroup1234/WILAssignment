package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.DeleteGoalObject;
import com.a17001922.wil_app.goals.ReturnAllGoalObject;
import com.a17001922.wil_app.goals.ReturnAnyTypeGoalObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import com.a17001922.wil_app.goals.goalsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class deleteGoals extends AppCompatActivity  implements AdapterView.OnItemSelectedListener
{
    //_____________Declarations_________________
    Button btnBack, btnDelete;
    private static final String TAG = "DeleteGoalsActivity";
    Context context;
    ProgressBar progressBar;
    Spinner cmbListOfUserGoals;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<ReturnAnyTypeGoalObject> allListedGoals;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_goals);

        //_____________Binding fields and widgets_____________

        btnBack = findViewById(R.id.btnBackDeleteGoals);
        btnDelete = findViewById(R.id.btnDeleteGoal);
        context = getApplicationContext();
        progressBar = findViewById(R.id.pBarDeleteGoals);
        progressBar.setVisibility(View.INVISIBLE);
        cmbListOfUserGoals = findViewById(R.id.cmbListOfUserGoals);



        //Get and display user goals
        if(StaticClass.hasInternet)
        {
            if(StaticClass.ongoingOperation == false)
            {
                StaticClass.ongoingOperation = true;
                progressBar.setVisibility(View.VISIBLE);

                try
                {
                    GetAndDisplayUserGoals();
                }
                catch (Exception e)
                {
                    Log.e(TAG, " " + e.getMessage());
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }
        }



        //_____________Back button Click Event Listener_____________

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    GoBack();
                }
                else
                {
                    Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);


                    boolean valid = false;
                    String errorMessage = "Select a goal from the available list and try again..";

                    DeleteGoalObject usersGoal = new DeleteGoalObject ();

                    try
                    {
                        usersGoal.setEmail(StaticClass.currentUser);

                        String goalName = cmbListOfUserGoals.getSelectedItem().toString();

                        String[] parts = goalName.split("_");
                        String goalid = parts[1];
                        String normal = parts[2];

                        if(normal.equals("(Normal)"))
                        {
                            usersGoal.setNormal(true);
                        }
                        else
                        {
                            usersGoal.setNormal(false);
                        }

                        int gID = Integer.parseInt(goalid);

                        usersGoal.setGoalID(gID);

                        if(usersGoal.getGoalID() > 0 && usersGoal.getEmail().length() > 7)
                        {
                            valid = true;
                        }

                    }
                    catch(Exception e)
                    {
                        Toast.makeText(context, " getting user input: " + e.getMessage() , Toast.LENGTH_LONG).show();
                    }


                    if(valid)
                    {
                        Call<ReturnMessageObject> deleteGoal = service.deleteGoal(usersGoal);
                        deleteGoal.enqueue(new Callback<ReturnMessageObject>()
                        {
                            @Override
                            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                            {
                                if (response.isSuccessful())
                                {
                                    ReturnMessageObject returnMessage = response.body();

                                    if(returnMessage.getResult())
                                    {
                                        Toast.makeText(context, "Goal deleted",Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "goal deleted: " + returnMessage.getErrorMessage());
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);

                                        //Open Loading activity
                                        Intent intent = new Intent(context, LoadingActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(getApplicationContext(), "Error: goal not deleted",Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "error: goal not deleted: " + returnMessage.getErrorMessage());
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }


                                }
                            }

                            @Override
                            public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                            {
                                Toast.makeText(context, "error: can't connect", Toast.LENGTH_LONG).show();
                                Log.e(TAG, " OnFailure error: can't connect");
                                StaticClass.ongoingOperation = false;
                                progressBar.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(context, errorMessage ,Toast.LENGTH_SHORT).show();
                        Log.e(TAG, errorMessage);
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }


                }
                else
                {
                    Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    //Changes screen back to home screen
    public void GoBack()
    {
        //Open Home activity
        Intent intent = new Intent(context, homeActivity.class);
        startActivity(intent);
    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }

    public void GetAndDisplayUserGoals()
    {
        UserGoalObject userGoalObject = new UserGoalObject();
        userGoalObject.setEmail(StaticClass.currentUser);
        allListedGoals = new ArrayList<>();

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

                        String [] arr = new String [allListedGoals.size()];

                        if(allListedGoals.size() < 1)
                        {
                            Toast.makeText(context, "You have no goals added yet", Toast.LENGTH_SHORT);
                        }
                        else
                        {

                            int count = 0;
                            for (ReturnAnyTypeGoalObject goalObject: allListedGoals)
                            {

                                String line = goalObject.getGoalName() + "_" + goalObject.getGoalID();

                                if(goalObject.getNormalGoal())
                                {
                                    line = line + "_" + "(Normal)";
                                }
                                else
                                {
                                    line = line + "_" + "(Custom)";
                                }

                                arr[count] = line;
                                count++;
                            }

                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cmbListOfUserGoals.setAdapter(adapter);

                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                    else
                    {
                        Log.e(TAG, "ERROR retrieving user goals successfully");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }

                @Override
                public void onFailure(Call<ReturnAllGoalObject> call, Throwable t)
                {
                    Toast.makeText(context, "error: can't connect",Toast.LENGTH_LONG);
                    Log.e(TAG, " OnFailure error: can't connect");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        }
        catch(Exception e)
        {
            Toast.makeText(context, "An error occurred :(",Toast.LENGTH_LONG);
            Log.e(TAG, " Exception error: " + e.getMessage());
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

    }
}

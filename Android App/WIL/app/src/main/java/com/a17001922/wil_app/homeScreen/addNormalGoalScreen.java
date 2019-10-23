package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
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
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.ReturnGoalObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This class manages the Add Normal Goals Screen
public class addNormalGoalScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    //_____________Declarations_________________
    Button btnBack;
    List<Goal> allListedGoals;
    Spinner cmbListOfGoals;
    Button btnAddGoals;
    ArrayList<String> arrGoals = new ArrayList<String>();
    private static final String TAG = "AddNormalGoalActivity";
    public static ArrayList<Goal> publicGoalList = new ArrayList<Goal>();
    Context context;
    ProgressBar progressBar;

    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_normal_goal_screen);

        //_____________Binding fields and widgets_____________
        cmbListOfGoals = findViewById(R.id.cmbListOfGoals);
        btnAddGoals = findViewById(R.id.btnAddGoalScreen);
        final goalsService service = StaticClass.retrofit.create(goalsService.class);
        context = getApplicationContext();
        btnBack = findViewById(R.id.btnBackNormalGoal);
        progressBar = findViewById(R.id.pBarAddNormalGoal);
        progressBar.setVisibility(View.INVISIBLE);


        StaticClass.ongoingOperation = true;
        progressBar.setVisibility(View.VISIBLE);

        //Get goals
        final Call<ReturnGoalObject> getGoals = service.getAllGoals();
        try
        {
            getGoals.enqueue(new Callback<ReturnGoalObject>()
            {
                @Override
                public void onResponse(Call<ReturnGoalObject> call, Response<ReturnGoalObject> response)
                {
                    if (response.isSuccessful())
                    {
                        ReturnGoalObject returnGoalObject = response.body();

                        allListedGoals = returnGoalObject.getGoalList();


                        Log.e(TAG, " user goals retrieved successfully");

                        String [] arr = new String [allListedGoals.size()];

                        int count = 0;

                        for(Goal goal : allListedGoals)
                        {
                            String item = goal.getGoalID() + "-" + goal.getGoalName();
                            Log.e(TAG, " ITEM: " + item);
                            arr[count] = item;
                            count++;
                        }


                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arr);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        cmbListOfGoals.setAdapter(adapter);
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
                public void onFailure(Call<ReturnGoalObject> call, Throwable t)
                {
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG).show();
            Log.e(TAG, " Exception error: can't connect");
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }




//_____________Add Goals button Click Event Listener_____________
        btnAddGoals.setOnClickListener(new View.OnClickListener()
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



                    UserGoalObject usersGoal = new UserGoalObject();

                    try
                    {
                        usersGoal.setEmail(StaticClass.currentUser);

                        String goalName = cmbListOfGoals.getSelectedItem().toString();

                        String[] parts = goalName.split("-");
                        String goalid = parts[0];

                        int gID = Integer.parseInt(goalid);

                        usersGoal.setGoalId(gID);

                        if(usersGoal.getGoalId() > 0 && usersGoal.getEmail().length() > 7)
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
                        Call<ReturnMessageObject> addingGoal = service.addingGoal(usersGoal);
                        addingGoal.enqueue(new Callback<ReturnMessageObject>()
                        {
                            @Override
                            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                            {
                                if (response.isSuccessful())
                                {
                                    ReturnMessageObject returnMessage = response.body();

                                    if(returnMessage.getResult())
                                    {
                                        Toast.makeText(getApplicationContext(), "Goal added",Toast.LENGTH_SHORT).show();
                                        Log.e(TAG, "goal added: " + returnMessage.getErrorMessage());
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);

                                        //Open Loading activity
                                        Intent intent = new Intent(context, LoadingActivity.class);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        if(returnMessage.getErrorMessage().equals("duplicate"))
                                        {
                                            Toast.makeText(getApplicationContext(), "You already have this goal added...",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            Toast.makeText(getApplicationContext(), "Error, Goal not added",Toast.LENGTH_SHORT).show();
                                        }
                                        Log.e(TAG, "error: goal not added: " + returnMessage.getErrorMessage());
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }


                                }
                            }

                            @Override
                            public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                            {
                                Toast.makeText(getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG).show();
                                Log.e(TAG, " OnFailure error: can't connect");
                                StaticClass.ongoingOperation = false;
                                progressBar.setVisibility(View.INVISIBLE);

                                StaticClass.hasInternet = false;
                                //Open Login activity
                                Intent intent = new Intent(context, LoadingActivity.class);
                                startActivity(intent);
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
                    Toast.makeText(StaticClass.loginContext, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });



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
                    Toast.makeText(StaticClass.loginContext, "Please Wait...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent)
    {

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

}

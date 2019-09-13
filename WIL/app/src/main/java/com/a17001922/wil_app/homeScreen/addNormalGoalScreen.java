package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.Goal;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.ReturnGoalObject;
import com.a17001922.wil_app.goals.userGoalObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.R.layout.simple_spinner_item;

public class addNormalGoalScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener
{

    List<Goal> allListedGoals;
    Spinner cmbListOfGoals;
    Button btnAddGoals;
    ArrayList<String> arrGoals = new ArrayList<String>();
    private static final String TAG = "AddNormalGoalActivity";
    public static ArrayList<Goal> publicGoalList = new ArrayList<Goal>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_normal_goal_screen);
        cmbListOfGoals = findViewById(R.id.cmbListOfGoals);
        btnAddGoals = findViewById(R.id.btnAddGoalScreen);
        final goalsService service = StaticClass.retrofit.create(goalsService.class);





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

                    }
                    else
                    {
                        Log.e(TAG, "ERROR retrieving user goals successfully");
                    }
                }

                @Override
                public void onFailure(Call<ReturnGoalObject> call, Throwable t)
                {

                }
            });



            btnAddGoals.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    userGoalObject usersGoal = new userGoalObject();
                    usersGoal.setEmail(StaticClass.currentUser);

                    String goalName = cmbListOfGoals.getSelectedItem().toString();

                    String[] parts = goalName.split("-");
                    String goalid = parts[0];

                    usersGoal.setGoalId(goalid);

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
                                    Toast.makeText(getApplicationContext(), "Goal added",Toast.LENGTH_LONG);
                                    Log.e(TAG, "goal added: " + returnMessage.getErrorMessage());
                                }
                                else
                                {
                                    Log.e(TAG, "error: goal not added: " + returnMessage.getErrorMessage());
                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                        {
                            Toast.makeText(getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG);
                            Log.e(TAG, " OnFailure error: can't connect");
                        }
                    });



                }
            });

        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "error: can't connect",Toast.LENGTH_LONG);
            Log.e(TAG, " Exception error: can't connect");
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

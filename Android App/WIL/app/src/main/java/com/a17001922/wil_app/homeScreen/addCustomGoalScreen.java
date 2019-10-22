package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.CustomGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import com.google.api.client.util.DateTime;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This class manages the Add Custom Goals Screen
public class addCustomGoalScreen extends AppCompatActivity
{
    //_____________Declarations_________________
        Button btnCustomGoal;
        Button btnBack;
        EditText et_GoalName,et_GoalDescription;
        private static final String TAG = "AddCustomGoalActivity";
        Context context;
        ProgressBar progressBar;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_goal_screen);

        //_____________Binding fields and widgets_____________
        btnCustomGoal = findViewById(R.id.btnCreateCustomGoal);
        et_GoalName = findViewById(R.id.et_CustomGoalName);
        et_GoalDescription = findViewById(R.id.et_CustomGoalDescription);
        btnBack = findViewById(R.id.btnBackCustomeGoals);
        //#TODO ADDED THE FOLLOWING TO CUSTOM GOAL
        DatePicker datePicker = findViewById(R.id.dpCustomGoalEndDate);
        Calendar cal = Calendar.getInstance();
        int year = cal.YEAR;
        int day = cal.DAY_OF_MONTH;
        int month = cal.MONTH;
        Date date = new Date();
        datePicker.updateDate(year,month,day);
        datePicker.setMinDate(date.getTime());
        context = getApplicationContext();
        progressBar = findViewById(R.id.pBarAddCustomGoals);
        progressBar.setVisibility(View.INVISIBLE);


        //_____________Custom Goal button Click Event Listener_____________
        btnCustomGoal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);


                    String goalName = "", goalDescription = "", selectedDate = "";
                    boolean valid = false;
                    String errorMessage = "Fill in all fields, and try again";

                    try
                    {
                        goalName = et_GoalName.getText().toString();
                        goalDescription = et_GoalDescription.getText().toString();

                        if(goalName.contains("-"))
                        {
                            goalName = goalName.replace('-',' ');
                        }

                        if(goalDescription.contains("-"))
                        {
                            goalDescription = goalDescription.replace('-',' ');
                        }

                        String stringDay = "", stringMonth = "";
                        int thisMonth = month, dayOfMonth = day, thisYear = year;


                        thisMonth = datePicker.getMonth();
                        dayOfMonth = datePicker.getDayOfMonth();
                        thisYear = datePicker.getYear();



                        if (dayOfMonth < 10)
                        {
                            stringDay = "0" + dayOfMonth;
                        }
                        else
                        {
                            stringDay = dayOfMonth + "";
                        }

                        thisMonth++;

                        if (thisMonth < 10)
                        {
                            stringMonth = "0" + thisMonth;
                        }
                        else
                        {
                            stringMonth = thisMonth + "";
                        }



                        selectedDate = thisYear + "-" + stringMonth + "-" + stringDay;


                        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
                        Date date1 = null;
                        try {
                            date1 = df2.parse(selectedDate);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                        DateTime dateTime = new DateTime(date1);

                        Log.e(TAG,"final Date : "+ dateTime);
                        Log.e(TAG,"goal Name : "+goalName);
                        Log.e(TAG,"final Desc. : "+goalDescription);


                        if (goalName.length() > 1 && goalDescription.length() > 1 && selectedDate.length() > 1)
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
                        try
                        {
                            CustomGoalObject pushingGoal = new CustomGoalObject();
                            pushingGoal.setEmail(StaticClass.currentUser);
                            pushingGoal.setGoalName(goalName);
                            pushingGoal.setGoalDescription(goalDescription);
                            pushingGoal.setFinishDate(selectedDate);


                            goalsService service = StaticClass.retrofit.create(goalsService.class);
                            final Call<ReturnMessageObject> customGoalObjectCall = service.addingCustomGoal(pushingGoal);
                            customGoalObjectCall.enqueue(new Callback<ReturnMessageObject>() {
                                @Override
                                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                {

                                    if (response.isSuccessful())
                                    {
                                        ReturnMessageObject returnMessage = response.body();


                                        if (returnMessage.getResult())
                                        {
                                            Toast.makeText(context, "custom goal added successfully",Toast.LENGTH_LONG).show();
                                            Log.e(TAG, "custom goal added successfully");
                                            StaticClass.ongoingOperation = false;
                                            progressBar.setVisibility(View.INVISIBLE);

                                            //Open Loading activity
                                            Intent intent = new Intent(context, LoadingActivity.class);
                                            startActivity(intent);
                                        }
                                        else
                                        {
                                            Toast.makeText(context, "error: custom goal not added",Toast.LENGTH_LONG).show();
                                            Log.e(TAG, "error: custom goal not added: " + returnMessage.getErrorMessage());
                                            StaticClass.ongoingOperation = false;
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }


                                    }
                                }

                                @Override
                                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                {
                                    Toast.makeText(context, "not connected :(",Toast.LENGTH_LONG).show();
                                    Log.e(TAG, "error: not connected to api");
                                    StaticClass.ongoingOperation = false;
                                    progressBar.setVisibility(View.INVISIBLE);
                                }


                            });




                        }
                        catch (Exception e)
                        {
                            Toast.makeText(context, "An error occurred :(",Toast.LENGTH_LONG).show();
                            Log.e(TAG, "exception: " + e.getMessage());
                            StaticClass.ongoingOperation = false;
                            progressBar.setVisibility(View.INVISIBLE);
                        }
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

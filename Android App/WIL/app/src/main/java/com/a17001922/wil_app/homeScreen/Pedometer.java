package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.goalsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


//_________________________code attribution_______________________
// The code in this class was attributed from http://www.gadgetsaint.com:
//Author : Anu S Pillai
//Published March 30, 2017
// Updated April 25, 2017
// Link: http://www.gadgetsaint.com/android/create-pedometer-step-counter-android/#.W9CYIfaxXIw

//this class counts the user's steps.

//This class manages the Pedometer Screen
public class Pedometer extends AppCompatActivity implements SensorEventListener, StepListener
{
    //_____________Declarations_________________
    ProgressBar progressBar;
    Button btnBack, btnStart, btnSaveSteps;
    TextView txtTodaysSteps, txtCurrentSteps;
    goalsService goalService = StaticClass.retrofit.create(goalsService.class);
    private StepDetectorClass simpleStepDetector;
    private static SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private static final String TAG = "Pedometer Class";
    int todaysSteps = 0, currentSteps = 0, addSteps = 0;
    Context context;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);
    }


    //____________________OnStart Method_____________
    @Override
    protected void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        progressBar = findViewById(R.id.pBarPedometer);
        progressBar.setVisibility(View.INVISIBLE);
        btnBack = findViewById(R.id.btnBackPedometer);
        btnStart = findViewById(R.id.btnStartCountingSteps);
        btnSaveSteps = findViewById(R.id.btnSaveSteps);
        txtCurrentSteps = findViewById(R.id.txtCurrentSteps);
        txtTodaysSteps = findViewById(R.id.txtTodaysSteps);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetectorClass();
        simpleStepDetector.registerListener(this);
        context = getApplicationContext();


        StaticClass.ongoingOperation = true;
        progressBar.setVisibility(View.VISIBLE);

        if(StaticClass.hasInternet)
        {
          GetCurrentSteps();
        }
        else
        {
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }


        //_____________Back button Click Event Listener_____________
        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    ChangeFormHome();
                }
                else
                {
                    Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //_____________Start button Click Event Listener_____________
        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    Toast.makeText(context, "Start walking :)", Toast.LENGTH_SHORT).show();
                    numSteps = 0;
                    sensorManager.registerListener(Pedometer.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                }
                else
                {
                    Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //_____________Save Steps button Click Event Listener_____________
        btnSaveSteps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);

                    sensorManager.unregisterListener(Pedometer.this);

                    if (txtCurrentSteps.getText().length() > 0)
                    {
                        double sCount = Double.parseDouble(txtCurrentSteps.getText() + "");
                        addSteps = (int) sCount;
                        todaysSteps = todaysSteps + addSteps;

                        txtTodaysSteps.setText(todaysSteps + "");

                        UpdateSteps();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "No steps made...", Toast.LENGTH_SHORT).show();
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                        ChangeFormHome();
                    }
                }
                else
                {
                    Toast.makeText(StaticClass.loginContext, "Please Wait...", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


    // the following 3 methods update the step count when the sensor detects movement.
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            simpleStepDetector.updateAccel(event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }

    @Override
    public void step(long timeNs)
    {
        numSteps++;
        txtCurrentSteps.setText(numSteps + "");
    }


    //_____________This Method Gets the user's current step count for today and displays it_____________
    public void GetCurrentSteps()
    {
        UserStepsObject userStepsObject = new UserStepsObject();
        userStepsObject.setEmail(StaticClass.currentUser);

        try
        {
            final Call<UserStepsObject> userStepsCall = goalService.getUserSteps(userStepsObject);
            userStepsCall.enqueue(new Callback<UserStepsObject>()
            {
                @Override
                public void onResponse(Call<UserStepsObject> call, Response<UserStepsObject> response)
                {

                    UserStepsObject returnSteps = response.body();

                    todaysSteps = returnSteps.getNumSteps();
                    txtTodaysSteps.setText(returnSteps.getNumSteps() + "");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onFailure(Call<UserStepsObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user steps");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);

                    StaticClass.hasInternet = false;
                    //Open Login activity
                    Intent intent = new Intent(context, LoadingActivity.class);
                    startActivity(intent);
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving steps: " + e.toString());
            txtTodaysSteps.setText("Cannot Get steps - No Internet connection :(");
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }



    public void ChangeFormHome()
    {
        //Open Pedometer activity
        Intent intent = new Intent(getApplicationContext(), homeActivity.class);
        startActivity(intent);
    }


    public void UpdateSteps()
    {
        UserStepsObject userStepsObject = new UserStepsObject();
        userStepsObject.setEmail(StaticClass.currentUser);
        userStepsObject.setNumSteps(todaysSteps);

        try
        {
            final Call<ReturnMessageObject> updateStepsCall = goalService.updateStreak(userStepsObject);
            updateStepsCall.enqueue(new Callback<ReturnMessageObject>()
            {
                @Override
                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                {

                    ReturnMessageObject returnMessage = response.body();

                    if (returnMessage.getResult())
                    {
                        Toast.makeText(getApplicationContext(), "Today's Steps updated", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Steps Updated :)");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                        ChangeFormHome();
                    }
                    else
                    {
                        Log.e(TAG, "Steps Update Failed :(");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                        ChangeFormHome();
                    }

                }

                @Override
                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Update user steps");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);

                    StaticClass.hasInternet = false;
                    //Open Login activity
                    Intent intent = new Intent(context, LoadingActivity.class);
                    startActivity(intent);
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error updating steps: " + e.toString());
            txtTodaysSteps.setText("Cannot update steps - No Internet connection :(");
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
            ChangeFormHome();
        }
    }



}

package com.a17001922.wil_app.homeScreen;

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
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Streak;
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

public class Pedometer extends AppCompatActivity implements SensorEventListener, StepListener
{

    Button btnBack, btnStart, btnSaveSteps;

    TextView txtTodaysSteps, txtCurrentSteps;

    goalsService goalService = StaticClass.retrofit.create(goalsService.class);

    private StepDetectorClass simpleStepDetector;
    private static SensorManager sensorManager;
    private Sensor accel;
    private int numSteps;
    private static final String TAG = "Pedometer Class";

    int todaysSteps = 0, currentSteps = 0, addSteps = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedometer);


    }


    @Override
    protected void onStart()
    {
        super.onStart();

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

        GetCurrentSteps();

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ChangeFormHome();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                numSteps = 0;
                sensorManager.registerListener(Pedometer.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
            }
        });

        btnSaveSteps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                sensorManager.unregisterListener( Pedometer.this);

                if (txtCurrentSteps.getText().length() > 0)
                {
                    double sCount = Double.parseDouble(txtCurrentSteps.getText() + "");
                    addSteps = (int) sCount;
                    todaysSteps = todaysSteps + addSteps;

                    txtTodaysSteps.setText(todaysSteps + "");

                    UpdateSteps();
                    ChangeFormHome();
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

                }

                @Override
                public void onFailure(Call<UserStepsObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user steps");
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving steps: " + e.toString());
            txtTodaysSteps.setText("Cannot Get steps - No Internet connection :(");
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
                    }
                    else
                    {
                        Log.e(TAG, "Steps Update Failed :(");
                    }

                }

                @Override
                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Update user steps");
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error updating steps: " + e.toString());
            txtTodaysSteps.setText("Cannot update steps - No Internet connection :(");
        }
    }



}

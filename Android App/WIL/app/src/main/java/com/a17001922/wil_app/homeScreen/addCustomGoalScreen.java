package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a17001922.wil_app.R;

import java.util.Calendar;
import java.util.Date;

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
        btnBack = findViewById(R.id.btnBackCustomGoal);
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
        progressBar = findViewById(R.id.pBarAddCustomGoal);
        progressBar.setVisibility(View.INVISIBLE);


        //_____________Custom Goal button Click Event Listener_____________
        btnCustomGoal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(context, "Goal added", Toast.LENGTH_SHORT).show();
            }
        });



        //_____________Back button Click Event Listener_____________

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               GoBack();
            }
        });


    }

    //Changes screen back to home screen
    public void GoBack()
    {
        //Open Home activity
        Intent intent = new Intent(getApplication().getApplicationContext(), homeActivity.class);
        startActivity(intent);
    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


}

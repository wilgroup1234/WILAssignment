package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.customGoalObject;
import com.a17001922.wil_app.goals.goalsService;
import com.google.api.client.util.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addCustomGoalScreen extends AppCompatActivity {
    Button btnCustomGoal;
    EditText et_GoalName, et_GoalDescription;
    DatePicker datePicker;
    private static final String TAG = "AddCustomGoalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_goal_screen);
        btnCustomGoal = findViewById(R.id.btnCreateCustomGoal);
        et_GoalName = findViewById(R.id.et_CustomGoalName);
        et_GoalDescription = findViewById(R.id.et_CustomGoalDescription);
        datePicker = findViewById(R.id.dp_customGoalDatePicker);
        Calendar cal = Calendar.getInstance();
        Date minDate = new Date();
        int year= cal.YEAR;
        int month = cal.MONTH;
        int day = cal.DAY_OF_MONTH;
        datePicker.updateDate(year,month,day);
        datePicker.setMinDate(minDate.getTime());


        btnCustomGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customGoalObject pushingGoal = new customGoalObject();

                String goalName = et_GoalName.getText().toString();
                String goalDescription = et_GoalDescription.getText().toString();
                String goalEndDate = datePicker.getYear() + "/" + datePicker.getMonth() + "/" + datePicker.getDayOfMonth();
                SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                Date currentDate = new Date();
                Date goalDate = new Date();
                try {
                    currentDate = df.parse((currentDate.getTime() + "").trim());
                    goalDate = df.parse(goalEndDate);
                    if (goalDate.equals(currentDate)) {
                        Toast.makeText(addCustomGoalScreen.this, "Goal end date cant be the same date as today", Toast.LENGTH_SHORT).show();
                    } else {
                        DateTime variableToObject = DateTime.parseRfc3339(goalEndDate);


                        pushingGoal.setEmail(StaticClass.currentUser);
                        pushingGoal.setGoalName(goalName);
                        pushingGoal.setGoalDescription(goalDescription);
                        pushingGoal.setFinishDate(variableToObject);
                        try {

                            goalsService service = StaticClass.retrofit.create(goalsService.class);
                            final Call<ReturnMessageObject> customGoalObjectCall = service.addingCustomGoal(pushingGoal);
                            customGoalObjectCall.enqueue(new Callback<ReturnMessageObject>() {
                                @Override
                                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response) {


                                    if (response.isSuccessful()) {
                                        ReturnMessageObject returnMessage = new ReturnMessageObject();
                                        returnMessage = response.body();

                                        if (returnMessage.getResult()) {
                                            Toast.makeText(getApplicationContext(), "custom goal added successfully", Toast.LENGTH_LONG);
                                            Log.e(TAG, "custom goal added successfully");
                                        } else {
                                            Toast.makeText(getApplicationContext(), "error: custom goal not added", Toast.LENGTH_LONG);
                                            Log.e(TAG, "error: custom goal not added: " + returnMessage.getErrorMessage());

                                        }


                                    }
                                }

                                @Override
                                public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "not connected :(", Toast.LENGTH_LONG);
                                    Log.e(TAG, "error: not connected to api");
                                }


                            });


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "An error occurred :(", Toast.LENGTH_LONG);
                            Log.e(TAG, "exception: " + e.getMessage());
                        }
                    }


                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}

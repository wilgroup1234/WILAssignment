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

public class addCustomGoalScreen extends AppCompatActivity
{
        Button btnCustomGoal;
        EditText et_GoalName,et_GoalDescription;
        private static final String TAG = "AddCustomGoalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_goal_screen);
        btnCustomGoal=findViewById(R.id.btnCreateCustomGoal);
        et_GoalName=findViewById(R.id.et_CustomGoalName);
        et_GoalDescription=findViewById(R.id.et_CustomGoalDescription);
        //#TODO ADDED THE FOLLOWING TO CUSTOM GOAL
        DatePicker datePicker = findViewById(R.id.dpCustomGoalEndDate);
        Calendar cal = Calendar.getInstance();
        int year=cal.YEAR;
        int day = cal.DAY_OF_MONTH;
        int month=cal.MONTH;
        Date date = new Date();
        datePicker.updateDate(year,month,day);
        datePicker.setMinDate(date.getTime());

        btnCustomGoal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                String goalName = et_GoalName.getText().toString();
                String goalDescription = et_GoalDescription.getText().toString();

                String stringDay = "", stringMonth = "", selectedDate = "";
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
                                    Toast.makeText(getApplicationContext(), "custom goal added successfully",Toast.LENGTH_LONG);
                                    Log.e(TAG, "custom goal added successfully");
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "error: custom goal not added",Toast.LENGTH_LONG);
                                    Log.e(TAG, "error: custom goal not added: " + returnMessage.getErrorMessage());

                                }


                            }
                        }

                        @Override
                        public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                        {
                            Toast.makeText(getApplicationContext(), "not connected :(",Toast.LENGTH_LONG);
                            Log.e(TAG, "error: not connected to api");
                        }


                    });




                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "An error occurred :(",Toast.LENGTH_LONG);
                    Log.e(TAG, "exception: " + e.getMessage());
                }

            }
        });
    }
}

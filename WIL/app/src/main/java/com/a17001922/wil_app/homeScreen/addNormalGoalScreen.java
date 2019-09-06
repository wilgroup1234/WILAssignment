package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.goals;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.userGoalObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addNormalGoalScreen extends AppCompatActivity {
    List<goals> allListedGoals;
    Spinner cmbListOfGoals;
    Button btnAddGoals;
    ArrayList<String> arrGoals = new ArrayList<>();
    boolean flag = false;
    boolean finalFlag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_normal_goal_screen);
        cmbListOfGoals = findViewById(R.id.cmbListOfGoals);
        btnAddGoals = findViewById(R.id.btnAddGoalScreen);
        final goalsService service = StaticClass.retrofit.create(goalsService.class);
        final Call<List<goals>> getGoals = service.getAllGoals();


        getGoals.enqueue(new Callback<List<goals>>() {
            @Override
            public void onResponse(Call<List<goals>> call, Response<List<goals>> response) {
                if (!response.isSuccessful()) {

                } else {
                    allListedGoals = response.body();
                    if (allListedGoals == null) {
                        Toast.makeText(getApplicationContext(), "Unfortunately we had an error retrieving the list of goals to choose from.", Toast.LENGTH_SHORT).show();
                    } else {

                        flag = true;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<goals>> call, Throwable t) {

            }
        });
        if (flag) {
            for (goals item : allListedGoals) {
                arrGoals.add(item.getGoalName());
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrGoals);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cmbListOfGoals.setAdapter(arrayAdapter);

            btnAddGoals.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flag = false;
                    userGoalObject usersGoal = new userGoalObject();
                    String goalName = cmbListOfGoals.getSelectedItem().toString();
                    for (goals item : allListedGoals) {
                        if (item.getGoalName().equals(goalName)) {
                            int goalID = item.getGoalID();
                            usersGoal.setEmail(StaticClass.currentUser);
                            usersGoal.setGoalId((goalID + "").trim());
                            flag =true;
                        }
                    }if(flag)
                    {
                        Call<userGoalObject> addingGoal = service.addingGoal(usersGoal);
                        addingGoal.enqueue(new Callback<userGoalObject>() {
                            @Override
                            public void onResponse(Call<userGoalObject> call, Response<userGoalObject> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(addNormalGoalScreen.this, "New Goal Added successfully", Toast.LENGTH_LONG).show();
                                    finalFlag=true;

                                }else{
                                    Toast.makeText(addNormalGoalScreen.this, "we Encountered an error with adding your goal please try again later" +
                                            " or make sure you connected to the internet", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<userGoalObject> call, Throwable t) {
                                Toast.makeText(addNormalGoalScreen.this, "we Encountered an error with adding your goal please try again later" +
                                        " or make sure you connected to the internet as we had trouble connecting to our services", Toast.LENGTH_LONG).show();
                            }
                        });

                    }

                }
            });
        }
    }
}

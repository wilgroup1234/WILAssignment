package com.a17001922.wil_app.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.customGoalObject;
import com.a17001922.wil_app.goals.goals;
import com.a17001922.wil_app.goals.goalsService;
import com.a17001922.wil_app.goals.userGoalObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addCustomGoalScreen extends AppCompatActivity {
        Button btnCustomGoal;
        EditText et_GoalName,et_GoalDescription;
    private userGoalObject addingCustomGoal;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_custom_goal_screen);
        btnCustomGoal=findViewById(R.id.btnCreateCustomGoal);
        et_GoalName=findViewById(R.id.et_CustomGoalName);
        et_GoalDescription=findViewById(R.id.et_CustomGoalDescription);

        btnCustomGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag =false;
                customGoalObject pushingGoal = new customGoalObject();

                String goalName = et_GoalName.getText().toString();
                String goalDescription = et_GoalDescription.getText().toString();

                pushingGoal.setEmail(StaticClass.currentUser);
                pushingGoal.setGoalName(goalName);
                pushingGoal.setGoalDescription(goalDescription);

                try{
                    addingCustomGoal = new userGoalObject();
                    goalsService service = StaticClass.retrofit.create(goalsService.class);
                    final Call<userGoalObject> customGoalObjectCall = service.addingCustomGoal(pushingGoal);
                    customGoalObjectCall.enqueue(new Callback<userGoalObject>() {
                        @Override
                        public void onResponse(Call<userGoalObject> call, Response<userGoalObject> response) {
                            if (!response.isSuccessful()) {

                            } else {
                                addingCustomGoal = response.body();
                                flag = true;
                            }
                        }

                        @Override
                        public void onFailure(Call<userGoalObject> call, Throwable t) {

                        }
                    });




                }catch (Exception e){

                }

            }
        });
    }
}

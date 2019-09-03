package com.a17001922.wil_app.homeScreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.goals;
import com.a17001922.wil_app.goals.goalsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addNormalGoalScreen extends AppCompatActivity {
    List<goals>allListedGoals;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_normal_goal_screen);
        goalsService service = StaticClass.retrofit.create(goalsService.class);
        final Call<List<goals>> getGoals=service.getAllGoals();
        getGoals.enqueue(new Callback<List<goals>>() {
            @Override
            public void onResponse(Call<List<goals>> call, Response<List<goals>> response) {
                if (!response.isSuccessful()) {

                } else {
                   allListedGoals = response.body();
                    if(allListedGoals== null){
                        Toast.makeText(getApplicationContext(), "Unfortunately we had an error retrieving the list of goals to choose from.", Toast.LENGTH_SHORT).show();
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<List<goals>> call, Throwable t) {

            }
        });
    }
}

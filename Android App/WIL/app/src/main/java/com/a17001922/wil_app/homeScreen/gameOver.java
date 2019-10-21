package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.LeaderboardObject;
import com.a17001922.wil_app.goals.goalsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This class manages the Game Over Screen
public class gameOver extends AppCompatActivity
{
    //_____________Declarations_________________
    Button btnBack;
    private static final String TAG = "GameOverActivity";
    Context context;
    TextView txtUserScore;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        //_____________Binding fields and widgets_____________
        btnBack = findViewById(R.id.btnLeaderboards);
        context = getApplicationContext();
        txtUserScore = findViewById(R.id.txtUserScore);


        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OpenLeaderboardsScreen();
            }
        });

        txtUserScore.setText(StaticClass.playerScore + "");


        if(StaticClass.hasInternet)
        {
            LeaderboardObject leaderboardObject = new LeaderboardObject();
            leaderboardObject.setScore(StaticClass.playerScore);
            leaderboardObject.setEmail(StaticClass.currentUser);


            //Upload score

            try
            {
                Call<ReturnMessageObject> pushScore = service.uploadScore(leaderboardObject);
                pushScore.enqueue(new Callback<ReturnMessageObject>()
                {
                    @Override
                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                    {
                        if (response.isSuccessful())
                        {
                            ReturnMessageObject returnMessage = response.body();
                            if(returnMessage.getResult())
                            {
                                Log.e(TAG, " Score uploaded :)");
                            }
                            else
                            {
                                Log.e(TAG, " Score upload error :(");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                    {
                        Toast.makeText(context, "error: can't connect",Toast.LENGTH_LONG).show();
                        Log.e(TAG, " OnFailure error: can't connect");
                    }
                });
            }
            catch(Exception e)
            {
                Log.e(TAG, " Exception: " + e.getMessage());
                Toast.makeText(context, "error: can't connect",Toast.LENGTH_LONG).show();
            }


        }
        else
        {
            Toast.makeText(context, "Score not added to Leaderboards, No internet connection :(", Toast.LENGTH_LONG);
            OpenGameScreen();
        }


    }



    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }

    //________Open Game Over Screen________
    public void OpenLeaderboardsScreen()
    {
        Intent intent = new Intent(context, Leaderboard.class);
        startActivity(intent);
    }

    //________Open Game Over Screen________
    public void OpenGameScreen()
    {
        Intent intent = new Intent(context, homeActivity.class);
        startActivity(intent);
    }



}

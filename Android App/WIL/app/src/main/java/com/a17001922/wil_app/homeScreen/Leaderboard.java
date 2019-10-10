package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.ReturnTopEight;
import com.a17001922.wil_app.goals.goalsService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Leaderboard extends AppCompatActivity
{
    //_____________Declarations_________________
    Button btnBack;
    TextView score1, score2, score3, score4, score5, score6, score7, score8;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    private static final String TAG = "LeaderBoardsActivity";
    Context context;
    ProgressBar progressBar;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboards);

        //_____________Binding fields and widgets_____________
        btnBack = findViewById(R.id.btnBackLeaderboards);
        context = getApplicationContext();
        progressBar = findViewById(R.id.pBarLeaderboard);
        progressBar.setVisibility(View.INVISIBLE);
        score1 = findViewById(R.id.score1);
        score2 = findViewById(R.id.score2);
        score3 = findViewById(R.id.score3);
        score4 = findViewById(R.id.score4);
        score5 = findViewById(R.id.score5);
        score6 = findViewById(R.id.score6);
        score7 = findViewById(R.id.score7);
        score8 = findViewById(R.id.score8);


        StaticClass.ongoingOperation = true;
        GetHighScores();



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
                    Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                }
            }
        });








    }

    //Changes screen back to Game Screen
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


    public void GetHighScores()
    {
        try
        {
            Call<ReturnTopEight> getScores = service.GetTopScores();
            getScores.enqueue(new Callback<ReturnTopEight>()
            {
                @Override
                public void onResponse(Call<ReturnTopEight> call, Response<ReturnTopEight> response)
                {
                    if (response.isSuccessful())
                    {
                        ReturnTopEight returnMessage = response.body();
                        String [] parts = {""};
                        String topEightList = "";

                        try
                        {
                            topEightList = returnMessage.getTopEight();

                             parts = topEightList.split("#");
                        }
                        catch(Exception e)
                        {
                           if(topEightList.length() > 2)
                           {
                               String temp = topEightList.replace("#", "");
                               parts[0] = temp;
                           }
                        }




                        try
                        {
                           String [] part = parts[0].split("@");

                           score1.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[1].split("@");

                            score2.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[2].split("@");

                            score3.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[3].split("@");

                            score4.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[4].split("@");

                            score5.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[5].split("@");

                            score6.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[6].split("@");

                            score7.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        try
                        {
                            String [] part = parts[7].split("@");

                            score8.setText(part[0] + ". " + part[1] + " Score: " + part[2]);
                        }
                        catch(Exception e)
                        {

                        }

                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);


                    }
                }

                @Override
                public void onFailure(Call<ReturnTopEight> call, Throwable t)
                {
                    Toast.makeText(context, "error: can't connect",Toast.LENGTH_LONG).show();
                    Log.e(TAG, " OnFailure error: can't connect");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        }
        catch(Exception e)
        {
            Log.e(TAG, " Exception: " + e.getMessage());
            Toast.makeText(context, "error: can't connect",Toast.LENGTH_LONG).show();
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


}

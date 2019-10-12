package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

import java.util.Timer;
import java.util.TimerTask;

public class gameActivity extends AppCompatActivity
{
    //_____________Declarations_________________
    private static final String TAG = "GameActivityActivity";
    Context context;

    Game gameView;
    private final static long TIMER_INTERVAL = 18;
    private Timer timer;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

        //_____________Binding fields and widgets_____________
        context = getApplicationContext();


        gameView = new Game(context);

        setContentView(gameView);


        timer = new Timer();
        timer.schedule( new TimerTask()
        {
            @Override
            public void run()
            {
                if (StaticClass.gameOver)
                {
                    OpenGameOverScreen();
                    timer.cancel();
                }
                else
                {
                    gameView.invalidate();

                }



            }
        }, 0, TIMER_INTERVAL);



    }



    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }

    //________Open Game Over Screen________
    public void OpenGameOverScreen()
    {
        Intent intent = new Intent(context, gameOver.class);
        startActivity(intent);
    }


}
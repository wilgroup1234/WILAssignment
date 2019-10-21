package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;

//This class manages the Game Screen Fragment
public class GameScreen extends Fragment
{
    //_____________Declarations_________________
    View v;
    private static final String TAG = "GameActivity";
    Button btnPlay, btnLeaderboads, btnHowToPlay;


    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_game_screen,container,false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________

        btnHowToPlay = v.findViewById(R.id.btnHowToPlay);
        btnPlay = v.findViewById(R.id.btnPlayGame);
        btnLeaderboads = v.findViewById(R.id.btnViewLeaderboards);


        btnHowToPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                OpenHelpScreen();

            }
        });


        btnPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                StaticClass.playerScore = 0;
                StaticClass.gameOver = false;
                PlayGame();
            }
        });


        btnLeaderboads.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(StaticClass.hasInternet)
                {
                    OpenLeaderboards();
                }
                else
                {
                    Toast.makeText(StaticClass.homeContext, "Cannot view Leaderboards when offline :(", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    public void PlayGame()
    {
        //Open Login activity
        Intent intent = new Intent(StaticClass.homeContext, gameActivity.class);
        startActivity(intent);
    }

    public void OpenLeaderboards()
    {
        //Open Login activity
        Intent intent = new Intent(StaticClass.homeContext, Leaderboard.class);
        startActivity(intent);
    }

    public void OpenHelpScreen()
    {
        //Open Login activity
        Intent intent = new Intent(StaticClass.homeContext, helpScreen.class);
        startActivity(intent);
    }

}
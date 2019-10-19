package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.a17001922.wil_app.R;

public class offlineHelpActivity extends AppCompatActivity
{

    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_help);
        TextView txtOfflineHelp = findViewById(R.id.txtOfflineText);
        btnBack = findViewById(R.id.btnofflineBack);

        String toShow ="Help Page\n" +
                "\n" +
                "Home Page – This page where you can add custom and pre-set goals to your list of goals and also remove goals once you have completed them.\n" +
                "\n" +
                "Game Page- This page allows you to go to the game leaderboards or to start the game.\n" +
                "\n" +
                "Gratitude Page- This page allows you to enter the things that you are grateful for the day and submit them. You don’t have to enter them all at once, you can come back and fill in more later.\n" +
                "\n" +
                "Streak Page- This page just shows how many days (in a row) you have been logged in for.\n" +
                "\n" +
                "Upload Page- This page provides a link to the google drive app so that you can make use of your personal google drive to store the documents and have them wherever you need them.\n" +
                "\n" +
                "CV Page- This page provides a link to a sophisticated cv app that has access to multiple templates to give you an option for all situations.\n" +
                "\n" +
                "Planner Page- This page provides a link to the google play store to download google calendar.\n";

        txtOfflineHelp.setText(toShow);



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
        Intent intent = new Intent(getApplicationContext(), homeActivity.class);
        startActivity(intent);
    }

    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }
}

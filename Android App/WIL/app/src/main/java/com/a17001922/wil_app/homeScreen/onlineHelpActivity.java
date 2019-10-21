package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.a17001922.wil_app.R;

//This class manages the Online Help Screen
public class onlineHelpActivity extends AppCompatActivity
{
    //_____________Declarations_________________
    Button btnBack;

    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_help);
        btnBack = findViewById(R.id.btnOnlineBack);

        String toShow ="Help Page\n" +
                "\n" +
                "Home Page – This page where you can add custom and pre-set goals to your list of goals and also remove goals once you have completed them.\n" +
                "\n" +
                "View goals Page- This page allows you to see a list of your goals, are also able to mark off goals and save the changes from this page.\n" +
                "\n" +
                "View life skills page – This page shows you a list of important life skills you should be aiming to accomplish at some time. You are able to check off and save the changes you make on this screen as well.\n" +
                "\n" +
                "Daily Quote page- This page shows you a daily quote and provides a YouTube link to a motivational video as well.\n" +
                "\n" +
                "Game Page- This page allows you to go to the game leaderboards or to start the game.\n" +
                "\n" +
                "Gratitude Page- This page allows you to enter the things that you are grateful for the day and submit them. You don’t have to enter them all at once you can come back and fill in more later.\n" +
                "\n" +
                "Steps Page- This page tracks the number of steps that you take using your device's pedometer. It also allows you to your track steps for the day. Be sure to click the start button to start counting.\n" +
                "\n" +
                "Streak Page- This page just shows how many days (in a row) you have been logged in for.\n" +
                "\n" +
                "Upload Page- This page provides a link to the google drive app so that you can make use of your personal google drive to store the documents and have them wherever you need them.\n" +
                "\n" +
                "CV Page- This page provides a link to a sophisticated cv app that has access to multiple templates to give you an option for all situations.\n" +
                "\n" +
                "Planner Page- This page provides a link to the google play store to download google calendar.\n";

        //_____________Binding fields and widgets_____________
        TextView txtShow = findViewById(R.id.txtOnlineBody);
        txtShow.setText(toShow);



        //_____________Back button Click Event Listener_____________
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

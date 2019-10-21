package com.a17001922.wil_app.homeScreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import com.a17001922.wil_app.R;

//This class manages the Game Help Screen
public class helpScreen extends AppCompatActivity
{
    //_____________Declarations_________________
    Button btnBack;
    private static final String TAG = "HelpActivity";
    Context context;


    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        //_____________Binding fields and widgets_____________
        btnBack = findViewById(R.id.btnBackHelpScreen);
        context = getApplicationContext();


         btnBack.setOnClickListener(new View.OnClickListener()
         {
             @Override
             public void onClick(View v)
             {
                 OpenGameScreen();
             }
         });


    }



    //________Do nothing when the back button is pressed________
    @Override
    public void onBackPressed()
    {

    }


    //________Open Game Over Screen________
    public void OpenGameScreen()
    {
        Intent intent = new Intent(context, homeActivity.class);
        startActivity(intent);
    }


}
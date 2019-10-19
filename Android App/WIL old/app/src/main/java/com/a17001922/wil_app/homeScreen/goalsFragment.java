package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.mainLogin;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import static android.content.Context.MODE_PRIVATE;


public class goalsFragment extends Fragment
{
    //_____________Declarations_________________
    Button btnAddGoals, btnAddCustomGoals, btnDeleteGoals;
    View v;

    //____________________OnCreate Method_____________
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_goals, container, false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        btnAddGoals = v.findViewById(R.id.btn_AddGoal);
        btnAddCustomGoals = v.findViewById(R.id.btn_AddCustomGoal);
        btnDeleteGoals = v.findViewById(R.id.btn_DeleteGoals);



        //_____________Add Custom Goals button Click Event Listener_____________
        btnAddCustomGoals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(StaticClass.homeContext,addCustomGoalScreen.class);
                startActivity(i);
            }
        });

        //_____________Add Goals button Click Event Listener_____________
        btnAddGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StaticClass.homeContext,addNormalGoalScreen.class);
                startActivity(i);
            }
        });

        //_____________Add Custom Goals button Click Event Listener_____________
        btnDeleteGoals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(StaticClass.homeContext,deleteGoals.class);
                startActivity(i);
            }
        });


    }


    public void ResetUser()
    {
        //Set logged in user to false;
        SharedPreferences sharedPreferences = StaticClass.homeContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        String type = sharedPreferences.getString(StaticClass.LOGGED_IN_TYPE, "");
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, false);
        editor.commit();

        if (type.equals("google"))
        {
            try
            {
                GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();
                GoogleSignInClient gSignInClient = GoogleSignIn.getClient(StaticClass.homeContext, googleSignInOptions);

                gSignInClient.signOut();
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        Toast.makeText(StaticClass.homeContext, "Signed Out...", Toast.LENGTH_LONG).show();

        OpenLogin();
    }


    public void OpenLogin()
    {
        //Open Login activity
        Intent intent = new Intent(StaticClass.homeContext, mainLogin.class);
        StaticClass.currentUser = "No_User";
        startActivity(intent);
    }




}

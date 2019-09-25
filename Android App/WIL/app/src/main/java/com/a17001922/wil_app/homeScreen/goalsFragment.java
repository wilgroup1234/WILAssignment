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
    Button btnAddGoals, btnAddCustomGoals;
    View v;
    ImageView btnLogout;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

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
        btnLogout = v.findViewById(R.id.btnLogoutGoals);


        //_____________Logout button Click Event Listener_____________
        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ResetUser();
            }
        });

        //_____________Add Custom Goals button Click Event Listener_____________
        btnAddCustomGoals.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity().getApplicationContext(),addCustomGoalScreen.class);
                startActivity(i);
            }
        });

        //_____________Add Goals button Click Event Listener_____________
        btnAddGoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity().getApplicationContext(),addNormalGoalScreen.class);
                startActivity(i);
            }
        });


    }


    public void ResetUser()
    {


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

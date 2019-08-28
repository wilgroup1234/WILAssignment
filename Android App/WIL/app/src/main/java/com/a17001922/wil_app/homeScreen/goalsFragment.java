package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.LoginScreen.mainLogin;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.userGoalObject;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import static android.content.Context.MODE_PRIVATE;


public class goalsFragment extends Fragment
{
    private ListView lvGoals;
    Connection con;
    userGoalObject user = new userGoalObject();
    Button btnAddGoals, btnAddCustomGoals, btnViewGoals;
    View v;
    ImageView btnLogout;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_goals, container, false);



        return v;
    }


    @Override
    public void onStart()
    {
        super.onStart();



        btnAddGoals = v.findViewById(R.id.btn_AddGoal);
        btnAddCustomGoals = v.findViewById(R.id.btn_AddCustomGoal);
        btnViewGoals = v.findViewById(R.id.btn_ViewGoals);
        btnLogout = v.findViewById(R.id.btnLogoutGoals);


        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ResetUser();
            }
        });



    }


    public void ResetUser()
    {
        //Set logged in user to false;
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
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
                GoogleSignInClient gSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), googleSignInOptions);

                gSignInClient.signOut();
            }
            catch(NullPointerException e)
            {
                e.printStackTrace();
            }
        }

        Toast.makeText(getActivity().getApplicationContext(), "Signed Out...", Toast.LENGTH_LONG).show();

        OpenLogin();
    }


    public void OpenLogin()
    {
        //Open Login activity
        Intent intent = new Intent(getActivity().getApplicationContext(), mainLogin.class);
        StaticClass.currentUser = "No_User";
        startActivity(intent);
    }




}

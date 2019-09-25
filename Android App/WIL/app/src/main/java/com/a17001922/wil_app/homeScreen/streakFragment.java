package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.a17001922.wil_app.LoginScreen.LoginUserObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.Streak;
import com.a17001922.wil_app.goals.goalsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class streakFragment extends Fragment
{
    //_____________Declarations_________________
    View v;

    int currentStreak = 0;
    private static final String TAG = "StreakActivity";
    TextView txtCurrentStreak;


    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_streak,container,false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        txtCurrentStreak = v.findViewById(R.id.txtCurrentStreak);



    }
}

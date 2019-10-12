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
    goalsService goalService = StaticClass.retrofit.create(goalsService.class);
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
        LoginUserObject loginUserObject = new LoginUserObject();
        loginUserObject.setEmail(StaticClass.currentUser);

        //API call to get user's streak
        try
        {
            final Call<Streak> streakCall = goalService.getUserStreak(loginUserObject);
            streakCall.enqueue(new Callback<Streak>() {
                @Override
                public void onResponse(Call<Streak> call, Response<Streak> response) {

                    Streak returnStreak = response.body();
                    currentStreak = returnStreak.getStreakLength();
                    txtCurrentStreak.setText(currentStreak + "");

                }

                @Override
                public void onFailure(Call<Streak> call, Throwable t)
                {
                    Log.e(TAG, "Connection onFailure Get user streak");
                }


            });

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error retrieving streak: " + e.toString());
            txtCurrentStreak.setText("Cannot Get streak - No Internet connection :(");
        }
    }
}

package com.a17001922.wil_app.homeScreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.ReturnAllGoalObject;
import com.a17001922.wil_app.goals.ReturnAnyTypeGoalObject;
import com.a17001922.wil_app.goals.UserGoalObject;
import com.a17001922.wil_app.goals.goalsService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class viewGoalsFragment extends Fragment
{
    //_____________Declarations_________________
    ProgressBar progressBar;
    View v;
    Button btnSaveGoalChanges;
    private ViewGoalsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    RecyclerView viewGoalsRecyclerView;
    private final String TAG = "View Goals Page";
    private final static int tickImage = R.drawable.greentick;
    private final static int exclamationImage = R.drawable.exclamationmark;
    private final static int crossImage = R.drawable.redx;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<ReturnAnyTypeGoalObject> allListedGoals;
    ArrayList<GoalsCheckedClass> originalGoalList = new ArrayList<>();
    ArrayList<GoalsCheckedClass> changedGoalList = new ArrayList<>();
    ArrayList<GoalsCheckedClass> updatedChangedGoalList = new ArrayList<>();
    int itemCount = 0;
    ArrayList<cardViewItem> cardList = new ArrayList<>();


    //____________________OnCreate Method_____________
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_view_goals, container, false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        progressBar = v.findViewById(R.id.pBarViewGoals);
        progressBar.setVisibility(View.INVISIBLE);
        btnSaveGoalChanges = v.findViewById(R.id.btnSaveGoalChanges);
        viewGoalsRecyclerView = v.findViewById(R.id.viewGoalsRecyclerView);
        viewGoalsRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(StaticClass.homeContext);

        //Display offline goals if not connected to the internet


        //_____________Save Goals button Click Event Listener_____________
        btnSaveGoalChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });


    }



}

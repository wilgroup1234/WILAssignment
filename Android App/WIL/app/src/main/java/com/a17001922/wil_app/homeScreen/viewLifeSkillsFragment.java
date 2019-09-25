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
import com.a17001922.wil_app.goals.LifeSkillObject;
import com.a17001922.wil_app.goals.ReturnLifeSkillsObject;
import com.a17001922.wil_app.goals.goalsService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class viewLifeSkillsFragment extends Fragment
{
    //_____________Declarations_________________
    ProgressBar progressBar;
    View v;
    Button btnSaveLifeSkillChanges;
    private ViewLifeSkillsAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    SharedPreferences sharedPreferences;
    RecyclerView viewLifeSkillsRecyclerView;
    private final String TAG = "View LifeSkills Page";
    private final static int tickImage = R.drawable.greentick;
    private final static int exclamationImage = R.drawable.exclamationmark;
    final goalsService service = StaticClass.retrofit.create(goalsService.class);
    List<LifeSkillObject> allListedLifeSkills;
    int itemCount = 0;
    ArrayList<LifeSkillChecked> originalLifeSkillsList = new ArrayList<>();
    ArrayList<LifeSkillChecked> changedLifeSkillsList = new ArrayList<>();
    ArrayList<LifeSkillChecked> updatedLifeSkillslList = new ArrayList<>();
    ArrayList<cardViewItem2> cardList = new ArrayList<>();



    //____________________OnCreate Method_____________
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_view_life_skills, container, false);
        return v;
    }

    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();
        //_____________Binding fields and widgets_____________
        progressBar = v.findViewById(R.id.pBarViewLifeSkills);
        progressBar.setVisibility(View.INVISIBLE);
        btnSaveLifeSkillChanges = v.findViewById(R.id.btnSaveLifeSkillChanges);
        viewLifeSkillsRecyclerView = v.findViewById(R.id.viewLifeSkillsRecyclerView);
        viewLifeSkillsRecyclerView.setHasFixedSize(true);
        recyclerViewLayoutManager = new LinearLayoutManager(StaticClass.homeContext);






        //_____________Save Life Skills button Click Event Listener_____________
        btnSaveLifeSkillChanges.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Toast.makeText(getActivity().getApplicationContext(), "life skills updated", Toast.LENGTH_SHORT).show();
            }
        });
    }




    // This method gets the user's current life skills and displays them

}

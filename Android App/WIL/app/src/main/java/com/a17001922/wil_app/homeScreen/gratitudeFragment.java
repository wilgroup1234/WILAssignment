package com.a17001922.wil_app.homeScreen;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a17001922.wil_app.LoginScreen.ReturnMessageObject;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.goals.goalsService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class gratitudeFragment extends Fragment
{
    //_____________Declarations_________________
    Button btnSave;
    View v;
    EditText txtItem1, txtItem2, txtItem3, txtItem4, txtItem5;
    String items = "";
    String item1 = "", item2 = "", item3 = "", item4 = "", item5 = "";
    private static final String TAG = "Gratitude Fragment";
    goalsService goalService = StaticClass.retrofit.create(goalsService.class);
    ProgressBar progressBar;

    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_gratitude, container,false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        btnSave = v.findViewById(R.id.btnSaveGratitude);
        txtItem1 = v.findViewById(R.id.et_Item1);
        txtItem2 = v.findViewById(R.id.et_Item2);
        txtItem3 = v.findViewById(R.id.et_Item3);
        txtItem4 = v.findViewById(R.id.et_Item4);
        txtItem5 = v.findViewById(R.id.et_Item5);
        progressBar = v.findViewById(R.id.pBarGratitude);
        progressBar.setVisibility(View.INVISIBLE);

        // Call method to show user's gratitude items for today




        //_____________Save button Click Event Listener_____________
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getActivity().getApplicationContext(), "Gratitude saved", Toast.LENGTH_SHORT).show();
            }
        });

    }


    //____This method get's a user's gratitude items for today_________



    //_____This method updates a user's gratitude items for Today_____

}

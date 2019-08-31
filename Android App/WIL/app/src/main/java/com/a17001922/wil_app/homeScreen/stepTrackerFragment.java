package com.a17001922.wil_app.homeScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.a17001922.wil_app.R;

public class stepTrackerFragment extends Fragment
{

    Button btnOpenPedometer;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_step_tracker,container,false);
        return v;


    }

    @Override
    public void onStart()
    {
        super.onStart();

        btnOpenPedometer = v.findViewById(R.id.btnOpenPedometer);

        btnOpenPedometer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                ChangeForm();
            }
        });


    }

    public void ChangeForm()
    {
        //Open Pedometer activity
        Intent intent = new Intent(getActivity().getApplicationContext(), Pedometer.class);
        startActivity(intent);
    }

}

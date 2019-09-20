package com.a17001922.wil_app.homeScreen;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.a17001922.wil_app.R;

public class PlannerFragment extends Fragment
{
    //_____________Declarations_________________
    ImageView calendarButton;
    View v;
    Uri uri;

    //____________________OnCreate Method_____________

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_planner,container,false);
        return v;
    }

    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();
        //_____________Binding fields and widgets_____________
        calendarButton = v.findViewById(R.id.imgCalendar);

        calendarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String calendarlink = "https://play.google.com/store/apps/details?id=com.google.android.calendar&hl=en";
                uri = Uri.parse(calendarlink);
                Intent intentSIX = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentSIX);
            }
        });

    }
}

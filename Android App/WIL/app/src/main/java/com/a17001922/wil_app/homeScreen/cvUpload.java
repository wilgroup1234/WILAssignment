package com.a17001922.wil_app.homeScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.Intent;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.a17001922.wil_app.StaticClass;

import com.a17001922.wil_app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class cvUpload extends Fragment
{
    //_____________Declarations_________________
    ImageView cvButton;
    View v;
    Uri uri;
    Button btnEnneagram;

    //____________________OnCreate Method_____________
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_cv_upload,container,false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();
        //_____________Binding fields and widgets_____________
        cvButton = v.findViewById(R.id.imgCV);
        btnEnneagram = v.findViewById(R.id.btnELink);

        cvButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String cvlink = "https://play.google.com/store/apps/details?id=icv.resume.curriculumvitae&hl=en";
                uri = Uri.parse(cvlink);
                Intent intentFIVE = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentFIVE);
            }
        });

        btnEnneagram.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    String link = "https://enneagramtest.net";
                    uri = Uri.parse(link);
                    Intent intentSIX = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intentSIX);
                }
                catch(Exception e)
                {
                    Toast.makeText(StaticClass.homeContext, "Unable to open link :(", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}

package com.a17001922.wil_app.homeScreen;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


import com.a17001922.wil_app.R;


public class uploadDocsFragment extends Fragment
{
    ImageView btnDrive;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_upload_docs,container,false);
        btnDrive = v.findViewById(R.id.imgGDrivee);

        btnDrive.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String cvlink = "https://play.google.com/store/apps/details?id=com.google.android.apps.docs&hl=en_ZA";
                Uri uri = Uri.parse(cvlink);
                Intent intentFIVE = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentFIVE);

            }
        });



        return v;



    }




}

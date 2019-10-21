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

//This class manages the Social Media Screen
public class socialMediaFragment extends Fragment
{
    //_____________Declarations_________________
    ImageView youtubeButton, faceboookButton, igButton, websiteButton;
    Uri uri;
    View v;

    //____________________OnCreate Method_____________
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
         v = inflater.inflate(R.layout.fragment_social_media, container, false);
        return v;
    }

    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________
        youtubeButton = v.findViewById(R.id.imgYoutube);
        faceboookButton = v.findViewById(R.id.imgFacebook);
        websiteButton = v.findViewById(R.id.imgWebsite);
        igButton  = v.findViewById(R.id.imgInsta);


        youtubeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //String youtubelink = "https://www.youtube.com";
                String youtubelink = "https://www.youtube.com/channel/UC_uNfeblBglyjftYLHa0dfA";
                uri = Uri.parse(youtubelink);
                Intent intentONE = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentONE);
            }
        });

        faceboookButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String facebooklink = "https://m.facebook.com/Goal-Pro-801066263410948/";
                uri = Uri.parse(facebooklink);
                Intent intentTWO = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentTWO);
            }
        });

        igButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String instagramlink = "https://instagram.com/goalpro.education?igshid=1y16jyin5c0c1";
                uri = Uri.parse(instagramlink);
                Intent intentTHREE = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentTHREE);
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String websitelink = "http://www.goalpro.co.za";
                uri = Uri.parse(websitelink);
                Intent intentFOUR = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentFOUR);
            }
        });

    }
}

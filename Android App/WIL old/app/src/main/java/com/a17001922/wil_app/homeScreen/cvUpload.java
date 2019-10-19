package com.a17001922.wil_app.homeScreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.support.v4.util.Pair;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.api.client.http.ByteArrayContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

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
        btnEnneagram = v.findViewById(R.id.btnEnneagramLink);

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
                String link = " https://enneagramtest.net/";
                uri = Uri.parse(link);
                Intent intentSIX = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intentSIX);
            }
        });

    }

}

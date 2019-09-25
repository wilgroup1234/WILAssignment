package com.a17001922.wil_app.LoginScreen;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragments extends Fragment
{
    //_____________Declarations_________________

    ProgressBar progressBar;
    Button btnRegister;
    EditText et_registerFirstName,et_registerSurname,et_registerEmail,et_age,et_registerPassword,et_confirmPassword;

    View v;
    String name,surname,email,password,confirmPassword="",message;
    private static final String TAG = "RegisterActivity";


    //____________________OnCreate Method_____________
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.activity_register,container,false);

        return v;
    }


    //____________________OnStart Method_____________
    public void onStart()
    {
        super.onStart();

        //_____________Binding fields and widgets_____________


        et_registerFirstName = v.findViewById(R.id.et_firstName);
        et_registerSurname = v.findViewById(R.id.et_surname);
        et_registerEmail=v.findViewById(R.id.et_registerEmail);
        et_age=v.findViewById(R.id.et_Age);
        et_registerPassword=v.findViewById(R.id.et_registerPassword);
        et_confirmPassword=v.findViewById(R.id.et_confirmPassword);
        btnRegister=v.findViewById(R.id.btnRegister);




        //_____________Register button Click Event Listener_____________
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent i = new Intent(getActivity().getApplicationContext(),homeActivity.class);
                startActivity(i);
            }
        });

    }

}

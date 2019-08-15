package com.a17001922.wil_app.LoginScreen;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.homeScreen.homeActivity;


public class LoginFragment extends Fragment
{
    Button btnLogin;
    EditText et_email,et_password;
    LoginUserObject user;
    Connection con;
    View v;

    private static final String TAG = "LoginActivity";
     @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
     {
         v = inflater.inflate(R.layout.activity_login,container,false);
         user = new LoginUserObject();
         con = new Connection();
        return v;
     }


    @Override
    public void onStart()
    {
        super.onStart();
        btnLogin=v.findViewById(R.id.btn_login);
        et_email=v.findViewById(R.id.et_userName);
        et_password=v.findViewById(R.id.et_Password);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = et_email.getText().toString();
                String password=et_password.getText().toString();
                user.setEmail(email);
                user.setPassword(password);
                String message;

                try
                {
                    if(con.userLogin(user) == false)
                    {
                        message="Invalid Login Details Entered :(";
                        Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        message="Login Successful :)";
                        Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                        Intent variables = new Intent(getContext(), homeActivity.class);
                        variables.putExtra("userEmail" , email);
                    }



                }
                catch (Exception e)
                {
                    message="Something Broke :(";
                    Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}

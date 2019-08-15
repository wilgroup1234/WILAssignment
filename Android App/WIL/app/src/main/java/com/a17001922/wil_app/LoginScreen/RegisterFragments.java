package com.a17001922.wil_app.LoginScreen;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.homeScreen.homeActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragments extends Fragment
{


    Button btnRegister;
    EditText et_registerFirstName,et_registerSurname,et_registerEmail,et_age,et_username,et_registerPassword,et_confirmPassword;
    RegisterUserObject user ;
    Connection connection;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_register_fragments,container,false);
        user =new RegisterUserObject();
        connection= new Connection();
        return v;
    }

    public void onStart()
    {
        super.onStart();
        et_registerFirstName = v.findViewById(R.id.et_registerFirstName);
        et_registerSurname = v.findViewById(R.id.et_registerSurname);
        et_registerEmail=v.findViewById(R.id.et_registerEmail);
        et_age=v.findViewById(R.id.et_registerAge);
        et_registerPassword=v.findViewById(R.id.et_registerPassword);
        et_confirmPassword=v.findViewById(R.id.et_confirmPassword);
        btnRegister=v.findViewById(R.id.btn_Register);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name,surname,email,password,confirmPassword="",message;
                name= et_registerFirstName.getText().toString();
                surname=et_registerSurname.getText().toString();
                int age = Integer.parseInt(et_age.getText().toString());
                email= et_registerEmail.getText().toString();
                password=et_registerPassword.getText().toString();
                confirmPassword=et_confirmPassword.getText().toString();

                user.setFirstName(name);
                user.setSurname(surname);
                user.setAge(age);
                user.setEmail(email);
                user.setPassword(password);
                user.setConfirmPassword(confirmPassword);


                try
                {
                    if(connection.userRegister(user) == false)
                    {
                        message="Invalid Details Entered :(";
                        Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        message="Register Successful :)";
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

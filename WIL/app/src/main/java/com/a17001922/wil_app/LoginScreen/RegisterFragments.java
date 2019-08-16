package com.a17001922.wil_app.LoginScreen;


import android.content.Intent;
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
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragments extends Fragment
{


    Button btnRegister;
    EditText et_registerFirstName,et_registerSurname,et_registerEmail,et_age,et_registerPassword,et_confirmPassword;
    RegisterUserObject user;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
    View v;
    String name,surname,email,password,confirmPassword="",message;
    private static final String TAG = "RegisterActivity";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.fragment_register_fragments,container,false);
        user =new RegisterUserObject();
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
                    final Call<ReturnMessageObject> registerUserCall = loginRegisterService.userRegister(user);
                    registerUserCall.enqueue(new Callback<ReturnMessageObject>()
                    {
                        @Override
                        public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                        {

                            ReturnMessageObject registeredAuth = response.body();
                            if (registeredAuth.getResult())
                            {
                                Log.e(TAG,"GetResult true");

                                Toast.makeText(getActivity().getApplicationContext(), "Register Successful" , Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getActivity().getApplicationContext(), homeActivity.class);
                                StaticClass.currentUser = email;
                                startActivity(intent);
                            }
                            else
                            {
                                Log.e(TAG,"GetResult false");
                                Toast.makeText(getActivity().getApplicationContext(), "Register Failed Invalid Details entered Bro :(" , Toast.LENGTH_LONG).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                        {
                            Log.e(TAG,"Connection onFailure");
                            Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(" , Toast.LENGTH_LONG).show();
                        }
                    });



                }
                catch (Exception e)
                {
                    message="Exception " + e.toString();
                    Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                }


          }
        });

    }



}

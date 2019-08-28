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
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
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
        v = inflater.inflate(R.layout.activity_register,container,false);
        user =new RegisterUserObject();
        return v;
    }

    public void onStart()
    {
        super.onStart();
        et_registerFirstName = v.findViewById(R.id.et_firstName);
        et_registerSurname = v.findViewById(R.id.et_surname);
        et_registerEmail=v.findViewById(R.id.et_registerEmail);
        et_age=v.findViewById(R.id.et_Age);
        et_registerPassword=v.findViewById(R.id.et_registerPassword);
        et_confirmPassword=v.findViewById(R.id.et_confirmPassword);
        btnRegister=v.findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (StaticClass.hasInternet)
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

                                    LogUserIn(email, "email");
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
                                Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :(" , Toast.LENGTH_LONG).show();
                            }
                        });



                    }
                    catch (Exception e)
                    {
                        message="Exception " + e.toString();
                        Toast.makeText(getActivity().getApplicationContext(),message , Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Cannot Register new User - No Internet connection :(", Toast.LENGTH_LONG).show();
                }


          }
        });

    }


    public void LogUserIn(String email, String type)
    {
        //Store user details in shared preferences
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, true);
        editor.putString(StaticClass.LOGGED_IN_USER_EMAIL, email);
        editor.putString(StaticClass.LOGGED_IN_TYPE, type);
        editor.commit();

        Toast.makeText(getActivity().getApplicationContext(), "user: " + email + " loggedin: " + sharedPreferences.getBoolean(StaticClass.LOGGED_IN_USER, false) , Toast.LENGTH_LONG).show();

        //Open Home activity
        Intent intent = new Intent(getActivity().getApplicationContext(), homeActivity.class);
        StaticClass.currentUser = email;
        startActivity(intent);
    }



}

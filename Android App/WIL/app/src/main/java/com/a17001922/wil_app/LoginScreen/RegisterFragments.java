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
    RegisterUserObject user;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
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
        progressBar = v.findViewById(R.id.pBarRegister);
        progressBar.setVisibility(View.INVISIBLE);
        et_registerFirstName = v.findViewById(R.id.et_firstName);
        et_registerSurname = v.findViewById(R.id.et_surname);
        et_registerEmail=v.findViewById(R.id.et_registerEmail);
        et_age=v.findViewById(R.id.et_Age);
        et_registerPassword=v.findViewById(R.id.et_registerPassword);
        et_confirmPassword=v.findViewById(R.id.et_confirmPassword);
        btnRegister=v.findViewById(R.id.btnRegister);
        user = new RegisterUserObject();



        //_____________Register button Click Event Listener_____________
        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);


                    //_____________Get data from fields_____________

                    Boolean valid = false;
                    String errorMessage = "Invalid Information Entered, Please correct this and try again...";

                    try
                    {
                        name = et_registerFirstName.getText().toString();
                        surname = et_registerSurname.getText().toString();
                        int age = Integer.parseInt(et_age.getText().toString());
                        email= et_registerEmail.getText().toString();
                        password = et_registerPassword.getText().toString();
                        confirmPassword = et_confirmPassword.getText().toString();

                        user.setFirstName(name);
                        user.setSurname(surname);
                        user.setAge(age);
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setConfirmPassword(confirmPassword);



                        //_____________Validate User Input_____________
                        if (password.equals(confirmPassword))
                        {
                            if(name.length() > 1 && surname.length()> 1 && age > 5 && email.length() > 7 && email.contains("@") && password.length() > 2)
                            {
                                valid = true;
                            }
                            else
                            {
                                errorMessage = "Invalid Information Entered, Please correct this and try again...";
                            }
                        }
                        else
                        {
                            errorMessage = "Passwords Don't Match";
                        }

                    }
                    catch(Exception e)
                    {
                        Log.e(TAG, " Trying to get and validate user input: " + e.getMessage());
                    }


                    //_____________If user input is valid, make API call to register User_____________
                    if (valid)
                    {
                        try
                        {
                            final Call<ReturnMessageObject> registerUserCall = loginRegisterService.userRegister(user);
                            registerUserCall.enqueue(new Callback<ReturnMessageObject>()
                            {
                                @Override
                                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                {
                                    //_____________If response is successful, log user in_____________

                                    ReturnMessageObject registeredAuth = response.body();
                                    if (registeredAuth.getResult())
                                    {
                                        Log.e(TAG,"GetResult true");
                                        Toast.makeText(StaticClass.loginContext, "Register Successful" , Toast.LENGTH_LONG).show();

                                        LogUserIn(email, "email");
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                    else
                                    {
                                        Log.e(TAG,"GetResult false");
                                        Toast.makeText(StaticClass.loginContext, "Register Failed: A Server error occurred :(" , Toast.LENGTH_LONG).show();
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }

                                }

                                @Override
                                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                {
                                    Log.e(TAG,"Connection onFailure");
                                    Toast.makeText(StaticClass.loginContext, "No Internet connection :(" , Toast.LENGTH_LONG).show();
                                    StaticClass.ongoingOperation = false;
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });



                        }
                        catch (Exception e)
                        {
                            Log.e(TAG," Exception caught while calling API register user method: " + e.getMessage());
                            Toast.makeText(StaticClass.loginContext ,message , Toast.LENGTH_LONG).show();
                            StaticClass.ongoingOperation = false;
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                    else
                    {
                        Log.e(TAG, errorMessage);
                        Toast.makeText(StaticClass.loginContext, errorMessage, Toast.LENGTH_LONG).show();
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
                else
                {
                    Toast.makeText(StaticClass.loginContext, "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    //_____________This Method logs a user into the app_____________
    public void LogUserIn(String email, String type)
    {
        //Store user details in shared preferences
        SharedPreferences sharedPreferences = StaticClass.loginContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, true);
        editor.putString(StaticClass.LOGGED_IN_USER_EMAIL, email);
        editor.putString(StaticClass.LOGGED_IN_TYPE, type);
        editor.commit();

        Toast.makeText(StaticClass.loginContext, "You are logged in...", Toast.LENGTH_LONG).show();

        //Open Home activity
        Intent intent = new Intent(getActivity().getApplicationContext(), homeActivity.class);
        StaticClass.currentUser = email;
        startActivity(intent);
    }



}

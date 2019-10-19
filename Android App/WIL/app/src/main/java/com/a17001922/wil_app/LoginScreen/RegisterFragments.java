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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.LoadingActivity;
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
    EditText et_registerFirstName,et_registerSurname,et_registerEmail,et_registerPassword,et_confirmPassword, answer;
    RegisterUserObject user;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
    View v;
    String name,surname,email,password,confirmPassword="",message, enteredAnswer, question;
    Spinner cmbListOfSecurityQuestions;
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
        et_registerPassword=v.findViewById(R.id.et_registerPassword);
        et_confirmPassword=v.findViewById(R.id.et_confirmPassword);
        btnRegister=v.findViewById(R.id.btnRegister);
        user = new RegisterUserObject();
        answer = v.findViewById(R.id.et_answer);
        cmbListOfSecurityQuestions = v.findViewById(R.id.cmbListSecurityQuestions);

        String [] arr = new String [3];
        arr[0] = "What is your pet's name?";
        arr[1] = "What is the name of your first friend?";
        arr[2] = "Who is your favourite superhero?";

        //populate list of security questions
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(StaticClass.loginContext, android.R.layout.simple_spinner_item, arr);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cmbListOfSecurityQuestions.setAdapter(adapter);



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
                        email= et_registerEmail.getText().toString();
                        password = et_registerPassword.getText().toString();
                        confirmPassword = et_confirmPassword.getText().toString();
                        enteredAnswer = answer.getText().toString();
                        question = cmbListOfSecurityQuestions.getSelectedItem().toString();



                        if(name.contains("#"))
                        {
                            name = name.replace('#',' ');
                        }
                        if(name.contains("@"))
                        {
                            name = name.replace('@',' ');
                        }

                        if(surname.contains("#"))
                        {
                            surname = surname.replace('#',' ');
                        }
                        if(surname.contains("@"))
                        {
                            surname = surname.replace('@',' ');
                        }


                        user.setFirstName(name);
                        user.setSurname(surname);
                        user.setEmail(email);
                        user.setPassword(password);
                        user.setConfirmPassword(confirmPassword);
                        user.setAnswer(enteredAnswer);
                        user.setSecurityQuestion(question);



                        //_____________Validate User Input_____________
                        if (password.equals(confirmPassword))
                        {
                            if(name.length() > 1 && surname.length()> 1 && email.length() > 7 && email.contains("@") && password.length() > 2 && answer.length() > 0 && question.length() > 0)
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
                                        Toast.makeText(StaticClass.loginContext, "Register Successful, You can now Login to your account..." , Toast.LENGTH_LONG).show();


                                        SharedPreferences sharedPreferences = StaticClass.loginContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedPreferences.edit();

                                        editor.putString(StaticClass.USER_GOALIDS, "");
                                        editor.putString(StaticClass.USER_GOALCOMPLETED, "");
                                        editor.putString(StaticClass.USER_GOALNAMES, "");
                                        editor.putString(StaticClass.USER_GOALTYPE, "");
                                        editor.putString(StaticClass.USER_GOALDESCRIPTIONS, "");
                                        editor.putString(StaticClass.USER_GOALDATES, "");
                                        editor.putString(StaticClass.USER_GOALCURRENTDATES, "");

                                        editor.putString(StaticClass.USER_LIFESKILLSIDS, "");
                                        editor.putString(StaticClass.USER_LIFESKILLSCOMPLETED, "");
                                        editor.putString(StaticClass.USER_LIFESKILLSNAMES, "");

                                        editor.putString(StaticClass.USER_DAILYQUOTEImage, "");
                                        editor.putString(StaticClass.USER_DAILYQUOTELINK, "");
                                        editor.putString(StaticClass.USER_DAILYQUOTETEXT, "");
                                        editor.putString(StaticClass.USER_Grats, "");
                                        editor.commit();


                                        LogUserIn(email, "email");

                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                    else
                                    {
                                        Log.e(TAG,"GetResult false");
                                        Toast.makeText(StaticClass.loginContext, "Invalid Details Entered, or this email is already being used." , Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(getActivity().getApplicationContext(), LoadingActivity.class);
        StaticClass.currentUser = email;
        startActivity(intent);
    }



}

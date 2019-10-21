package com.a17001922.wil_app.LoginScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This class is manages the Reset Password Screen
public class ResetPassword extends AppCompatActivity
{
    //_____________Declarations_________________
    EditText answer, emailAddress, password, confirmPassword;
    Spinner questions;
    Button save, cancel;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
    ProgressBar progressBar;
    private static final String TAG = "ResetPasswordActivity";
    String email, pass, confirmPass, Question = "", Answer;
    ResetPasswordObject user;

    //____________________OnCreate Method_____________
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        //_____________Declarations_________________
        answer = findViewById(R.id.et_answer2);
        questions = findViewById(R.id.cmbQuestions);
        save = findViewById(R.id.btnSave);
        cancel = findViewById(R.id.btnCancel);
        progressBar = findViewById(R.id.pBarResetPass);
        progressBar.setVisibility(View.INVISIBLE);
        emailAddress = findViewById(R.id.et_email2);
        password = findViewById(R.id.et_newPassword);
        confirmPassword = findViewById(R.id.et_confirmnewPassword2);

        try
        {
            String [] arr = new String [3];
            arr[0] = "What is your pet's name?";
            arr[1] = "What is the name of your first friend?";
            arr[2] = "Who is your favourite superhero?";

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(ResetPassword.this, android.R.layout.simple_spinner_item, arr);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            questions.setAdapter(adapter);
        }
        catch(Exception e)
        {

        }

        //_____________Save button Click Event Listener_____________
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Updates user password if all entered information is valid.
                if (!StaticClass.ongoingOperation)
                {
                    //Declarations
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);
                    Boolean valid = false;
                    String errorMessage = "Invalid Information Entered, Please correct this and try again...";

                    //_____________Get data from fields_____________
                    try
                    {
                        email = emailAddress.getText().toString();
                        pass = password.getText().toString();
                        confirmPass = confirmPassword.getText().toString();
                        Answer = answer.getText().toString();
                        Question = questions.getSelectedItem().toString();

                        user = new ResetPasswordObject();
                        user.setQuestion(Question);
                        user.setAnswer(Answer);
                        user.setEmail(email);
                        user.setNewPassword(pass);


                        //_____________Validate User Input_____________
                        if (pass.equals(confirmPass))
                        {
                            if(pass.length()> 1 && email.length() > 7 && email.contains("@") && answer.length() > 0 && Question.length() > 0)
                            {
                                valid = true;
                            }
                            else
                            {
                                errorMessage = "Invalid Information Entered...";
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
                            final Call<ReturnMessageObject> resetPassCall = loginRegisterService.resetPassword(user);
                            resetPassCall.enqueue(new Callback<ReturnMessageObject>()
                            {
                                @Override
                                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                {
                                    //_____________If response is successful, reset password_____________

                                    ReturnMessageObject registeredAuth = response.body();
                                    if (registeredAuth.getResult())
                                    {
                                        Log.e(TAG,"GetResult true");
                                        Toast.makeText(getApplicationContext(), "Reset Password Successful" , Toast.LENGTH_LONG).show();
                                        //Open Login activity
                                        Intent intent = new Intent(getApplicationContext(), mainLogin.class);
                                        startActivity(intent);

                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }
                                    else
                                    {
                                        Log.e(TAG,"GetResult false");
                                        Toast.makeText(getApplicationContext(), "Invalid Details Entered.." , Toast.LENGTH_LONG).show();
                                        StaticClass.ongoingOperation = false;
                                        progressBar.setVisibility(View.INVISIBLE);
                                    }

                                }

                                @Override
                                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                                {
                                    Log.e(TAG,"Connection onFailure");
                                    Toast.makeText(getApplicationContext(), "No Internet connection :(" , Toast.LENGTH_LONG).show();
                                    StaticClass.ongoingOperation = false;
                                    progressBar.setVisibility(View.INVISIBLE);
                                }
                            });



                        }
                        catch (Exception e)
                        {
                            Log.e(TAG," Exception caught while calling API register user method: " + e.getMessage());
                            Toast.makeText(getApplicationContext() , "error resetting password" , Toast.LENGTH_LONG).show();
                            StaticClass.ongoingOperation = false;
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                    else
                    {
                        Log.e(TAG, errorMessage);
                        Toast.makeText(getApplicationContext(), errorMessage, Toast.LENGTH_LONG).show();
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();
                }

            }
        });


        //_____________Cancel button Click Event Listener_____________
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);

                    //Open Login activity
                    Intent intent = new Intent(getApplicationContext(), mainLogin.class);
                    startActivity(intent);

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}

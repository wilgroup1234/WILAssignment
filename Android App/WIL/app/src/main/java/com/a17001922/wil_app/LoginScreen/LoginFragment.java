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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment
{
    //_____________Declarations_________________
    Button btnLogin;
    EditText et_email, et_password;
    LoginUserObject user;
    View v;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
    String email, password;
    ImageView googleSignInButton;
    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;
    String type = "email";
    private static final String TAG = "LoginActivity";
    ProgressBar progressBar;

//____________________OnCreate Method_____________
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.activity_login, container, false);
        return v;
    }


    //____________________OnStart Method_____________
    @Override
    public void onStart()
    {
        super.onStart();

        user = new LoginUserObject();


        //Google Sign-in object instantiations
         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), gso);


        //_____________Binding fields and widgets_____________
        btnLogin = v.findViewById(R.id.btnLogin);
        et_email = v.findViewById(R.id.et_Email);
        et_password = v.findViewById(R.id.et_LoginPassword);
        googleSignInButton = v.findViewById(R.id.imgGoogleLogin);
        progressBar = v.findViewById(R.id.pBarLogin);
        progressBar.setVisibility(View.INVISIBLE);

        CheckForLoggedInUser();


        //_____________Login button Click Event Listener_____________
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(!StaticClass.ongoingOperation)
                {
                    StaticClass.ongoingOperation = true;
                    progressBar.setVisibility(View.VISIBLE);

                    if (StaticClass.hasInternet)
                    {

                        boolean valid = false;
                        String errorMessage = "Invalid Login Details Entered, Please correct this and try again...";

                        try
                        {
                            email = et_email.getText().toString();
                            password = et_password.getText().toString();
                            user.setEmail(email);
                            user.setPassword(password);

                            if (email.length() > 7 && email.contains("@") && password.length() > 0)
                            {
                                valid = true;
                            }
                        }
                        catch(Exception e)
                        {
                            Log.e(TAG, " Trying to get and validate user input: " + e.getMessage());
                        }

                        if (valid)
                        {
                            try
                            {
                                final Call<ReturnMessageObject> loginUserCall = loginRegisterService.userLogin(user);
                                loginUserCall.enqueue(new Callback<ReturnMessageObject>() {
                                    @Override
                                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                                    {

                                        ReturnMessageObject loggedInAuth = response.body();
                                        if (loggedInAuth.getResult()) {
                                            Log.e(TAG, "GetResult true");

                                            Toast.makeText(StaticClass.loginContext, "Login Successful", Toast.LENGTH_SHORT).show();

                                            type = "email";
                                            LogUserIn();


                                        }
                                        else
                                        {
                                            Log.e(TAG, "GetResult false");
                                            Toast.makeText(StaticClass.loginContext, "Login Failed Invalid Details entered :(", Toast.LENGTH_SHORT).show();
                                            StaticClass.ongoingOperation = false;
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }


                                    }

                                    @Override
                                    public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                                        Log.e(TAG, "Connection onFailure");
                                        Toast.makeText(StaticClass.loginContext, "No Internet connection :(", Toast.LENGTH_LONG).show();

                                    }


                                });


                            }
                            catch (Exception e)
                            {
                                Log.e(TAG, "Exception " + e.toString());
                                Toast.makeText(StaticClass.loginContext, "Login Failed Invalid Details entered :(", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getActivity().getApplicationContext(), "Cannot Log in - No Internet connection :(", Toast.LENGTH_LONG).show();
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


        //_____________Google Sign-in button Click Event Listener_____________
        googleSignInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!StaticClass.ongoingOperation)
                {

                        if (StaticClass.hasInternet)
                        {
                            GoogleSignIn();
                        }
                        else
                        {
                            Toast.makeText(StaticClass.loginContext, "Cannot Log in - No Internet connection :(", Toast.LENGTH_LONG).show();
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
    public void LogUserIn()
    {
        //Store user details in shared preferences
        SharedPreferences sharedPreferences = StaticClass.loginContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, true);
        editor.putString(StaticClass.LOGGED_IN_USER_EMAIL, email);
        editor.putString(StaticClass.LOGGED_IN_TYPE, type);
        editor.commit();


        LoginUserObject loginUserObject = new LoginUserObject();
        loginUserObject.email = email;

        try
        {
            final Call<ReturnMessageObject> updateStreakCall = loginRegisterService.updateStreak(loginUserObject);
            updateStreakCall.enqueue(new Callback<ReturnMessageObject>()
            {
                @Override
                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                {
                    ReturnMessageObject loggedInAuth = response.body();

                    if (loggedInAuth.getResult())
                    {

                        Log.e(TAG, "Streak updated");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);

                    }
                    else
                    {
                        Log.e(TAG, "Streak update error :(");
                        StaticClass.ongoingOperation = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }


                }

                @Override
                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                {
                    Log.e(TAG, "UpdateStreak OnFailure - Cannot update Streak");
                    StaticClass.ongoingOperation = false;
                    progressBar.setVisibility(View.INVISIBLE);

                }


            });


        }
        catch (Exception e)
        {
            Log.e(TAG, "Exception " + e.toString());
            Toast.makeText(StaticClass.loginContext, "Login Failed Invalid Details entered :(", Toast.LENGTH_LONG).show();
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }


        Toast.makeText(StaticClass.loginContext, "You are logged in...", Toast.LENGTH_LONG).show();


        //Open Home activity
        Intent intent = new Intent(StaticClass.loginContext, homeActivity.class);
        StaticClass.currentUser = email;
        startActivity(intent);
    }


    //_____________This Method checks if a user is already logged in and bypasses the login screen if they are_____________
    public void CheckForLoggedInUser()
    {
        StaticClass.ongoingOperation = true;
        progressBar.setVisibility(View.VISIBLE);

        //Check shared preferences to see if user is already logged in
        SharedPreferences sharedPreferences = StaticClass.loginContext.getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        Boolean isLoggedIn = sharedPreferences.getBoolean(StaticClass.LOGGED_IN_USER, false);

        if (isLoggedIn)
        {
            email = sharedPreferences.getString(StaticClass.LOGGED_IN_USER_EMAIL, "");
            LogUserIn();
        }
        else
        {
            StaticClass.ongoingOperation = false;
            progressBar.setVisibility(View.INVISIBLE);
        }
    }


    //Google Sign-in Methods
    private void GoogleSignIn()
    {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 0)
        {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }


    private void handleSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try
        {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);


            email = account.getEmail();
            String name = account.getDisplayName();
            String surname = "a";
            if (account.getFamilyName() != null)
            {
                surname = account.getFamilyName();
            }
            String password = "a";

            GoogleSignInObject googleSignInObject = new GoogleSignInObject();
            googleSignInObject.setEmail(email);
            googleSignInObject.setFirstName(name);
            googleSignInObject.setLastName(surname);
            googleSignInObject.setPassword(password);


            //Create account if it doesn't exist
            try
            {
                final Call<ReturnMessageObject> googleSignInCall = loginRegisterService.googleSignIn(googleSignInObject);
                googleSignInCall.enqueue(new Callback<ReturnMessageObject>()
                {
                    @Override
                    public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                    {

                        ReturnMessageObject loggedInAuth = response.body();

                        if (loggedInAuth.getResult() == false)
                        {
                            Log.e(TAG, "Google sign in - New account created");
                        }
                        else
                        {
                            Log.e(TAG, "Google sign in - Account already exists");
                        }


                    }

                    @Override
                    public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                    {
                        Log.e(TAG, "Connection onFailure Google signin in OUR api call");
                        Toast.makeText(StaticClass.loginContext, " Google sign in OUR API No Internet connection :(", Toast.LENGTH_LONG).show();
                    }


                });


            }
            catch (Exception e)
            {
                Log.e(TAG, "Exception " + e.toString());
                Toast.makeText(StaticClass.loginContext, "Login Failed Invalid Details entered :(", Toast.LENGTH_LONG).show();
            }



            //Call login method to log user in
            type = "google";
            Toast.makeText(StaticClass.loginContext, "Google Sign-in Successful", Toast.LENGTH_LONG).show();
            LogUserIn();




        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.e(TAG, "Exception " + e.toString());
            Toast.makeText(StaticClass.loginContext, "Google Login Failed :(", Toast.LENGTH_LONG).show();

        }

    }




}

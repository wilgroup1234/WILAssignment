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
import android.widget.Toast;

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.PendingIntent.getActivity;
import static android.content.Context.MODE_PRIVATE;


public class LoginFragment extends Fragment
{
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        v = inflater.inflate(R.layout.activity_login, container, false);
        user = new LoginUserObject();

        CheckForLoggedInUser();

        return v;
    }


    @Override
    public void onStart()
    {
        super.onStart();




         gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getActivity().getApplicationContext(), gso);


        btnLogin = v.findViewById(R.id.btnLogin);
        et_email = v.findViewById(R.id.et_Email);
        et_password = v.findViewById(R.id.et_LoginPassword);
        googleSignInButton = v.findViewById(R.id.imgGoogleLogin);

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (StaticClass.hasInternet)
                {
                    email = et_email.getText().toString();
                    password = et_password.getText().toString();
                    user.setEmail(email);
                    user.setPassword(password);


                    try {
                        final Call<ReturnMessageObject> loginUserCall = loginRegisterService.userLogin(user);
                        loginUserCall.enqueue(new Callback<ReturnMessageObject>() {
                            @Override
                            public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response) {

                                ReturnMessageObject loggedInAuth = response.body();
                                if (loggedInAuth.getResult()) {
                                    Log.e(TAG, "GetResult true");

                                    Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

                                    type = "email";
                                    LogUserIn();


                                } else {
                                    Log.e(TAG, "GetResult false");
                                    Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
                                }


                            }

                            @Override
                            public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                                Log.e(TAG, "Connection onFailure");
                                Toast.makeText(getActivity().getApplicationContext(), "No Internet connection :(", Toast.LENGTH_LONG).show();

                            }


                        });


                    } catch (Exception e) {
                        Log.e(TAG, "Exception " + e.toString());
                        Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Cannot Log in - No Internet connection :(", Toast.LENGTH_LONG).show();
                }


            }
        });


        googleSignInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (StaticClass.hasInternet)
                {
                    GoogleSignIn();
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(), "Cannot Log in - No Internet connection :(", Toast.LENGTH_LONG).show();
                }

            }
        });

    }


    public void LogUserIn()
    {
        //Store user details in shared preferences
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(StaticClass.LOGGED_IN_USER, true);
        editor.putString(StaticClass.LOGGED_IN_USER_EMAIL, email);
        editor.putString(StaticClass.LOGGED_IN_TYPE, type);
        editor.commit();


        LoginUserObject loginUserObject = new LoginUserObject();
        loginUserObject.email = email;

        try
        {
            final Call<ReturnMessageObject> updateStreakCall = loginRegisterService.updateStreak(user);
            updateStreakCall.enqueue(new Callback<ReturnMessageObject>()
            {
                @Override
                public void onResponse(Call<ReturnMessageObject> call, Response<ReturnMessageObject> response)
                {
                    ReturnMessageObject loggedInAuth = response.body();

                    if (loggedInAuth.getResult())
                    {

                        Log.e(TAG, "Streak updated");

                    }
                    else
                    {
                        Log.e(TAG, "Streak update error :(");
                    }


                }

                @Override
                public void onFailure(Call<ReturnMessageObject> call, Throwable t)
                {
                    Log.e(TAG, "UpdateStreak OnFailure - Cannot update Streak");

                }


            });


        }
        catch (Exception e)
        {
            Log.e(TAG, "Exception " + e.toString());
            Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
        }


        Toast.makeText(getActivity().getApplicationContext(), "user: " + email + " loggedin: " + sharedPreferences.getBoolean(StaticClass.LOGGED_IN_USER, false) , Toast.LENGTH_LONG).show();

        //Open Home activity
        Intent intent = new Intent(getActivity().getApplicationContext(), homeActivity.class);
        StaticClass.currentUser = email;
        startActivity(intent);
    }

    public void CheckForLoggedInUser()
    {
        //Check shared preferences to see if user is already logged in
        SharedPreferences sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(StaticClass.SHARED_PREFS, MODE_PRIVATE);
        Boolean  isLoggedIn = sharedPreferences.getBoolean(StaticClass.LOGGED_IN_USER, false);

        if (isLoggedIn)
        {
            email = sharedPreferences.getString(StaticClass.LOGGED_IN_USER_EMAIL, "");
            LogUserIn();
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
            Log.e(TAG, "DUNZO1");
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            Log.e(TAG, "DUNZO2");

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


            //Check if account already exists

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
                        Toast.makeText(getActivity().getApplicationContext(), " Google sign in OUR API No Internet connection :(", Toast.LENGTH_LONG).show();
                    }


                });


            }
            catch (Exception e)
            {
                Log.e(TAG, "Exception " + e.toString());
                Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
            }



            type = "google";
            Toast.makeText(getActivity().getApplicationContext(), "Google Sign-in Successful", Toast.LENGTH_LONG).show();
            LogUserIn();




        }
        catch (ApiException e)
        {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            Log.e(TAG, "Exception " + e.toString());
            Toast.makeText(getActivity().getApplicationContext(), "Google Login Failed (API EXCEPTION) :(", Toast.LENGTH_LONG).show();

        }
    }






}

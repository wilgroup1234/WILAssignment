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

    String email, password;
    ImageView googleSignInButton;


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



        //_____________Binding fields and widgets_____________
        btnLogin = v.findViewById(R.id.btnLogin);
        et_email = v.findViewById(R.id.et_Email);
        et_password = v.findViewById(R.id.et_LoginPassword);
        googleSignInButton = v.findViewById(R.id.imgGoogleLogin);





        //_____________Login button Click Event Listener_____________
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Intent i = new Intent(getActivity().getApplicationContext(),homeActivity.class);
                startActivity(i);

            }
        });


        //_____________Google Sign-in button Click Event Listener_____________
        googleSignInButton.setOnClickListener(new View.OnClickListener()
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

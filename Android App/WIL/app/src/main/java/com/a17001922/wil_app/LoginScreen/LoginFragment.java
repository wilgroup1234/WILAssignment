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

import com.a17001922.wil_app.R;
import com.a17001922.wil_app.StaticClass;
import com.a17001922.wil_app.homeScreen.homeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    Button btnLogin;
    EditText et_email, et_password;
    LoginUserObject user;
    View v;
    loginRegisterService loginRegisterService = StaticClass.retrofit.create(loginRegisterService.class);
    String email, password;


    private static final String TAG = "LoginActivity";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_login, container, false);
        user = new LoginUserObject();
        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        btnLogin = v.findViewById(R.id.btnLogin);
        et_email = v.findViewById(R.id.et_Email);
        et_password = v.findViewById(R.id.et_LoginPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                            Log.e(TAG, loggedInAuth.result + " " + loggedInAuth.errorMessage);
                            if (loggedInAuth.getResult()) {
                                Log.e(TAG, "GetResult true");

                                Toast.makeText(getActivity().getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(getActivity().getApplicationContext(), homeActivity.class);
                                StaticClass.currentUser = email;
                                startActivity(intent);

                            } else {
                                Log.e(TAG, "GetResult false");
                                Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
                            }


                        }

                        @Override
                        public void onFailure(Call<ReturnMessageObject> call, Throwable t) {
                            Log.e(TAG, "Connection onFailure");
                            Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();

                        }


                    });


                } catch (Exception e) {
                    Log.e(TAG, "Exception " + e.toString());
                    Toast.makeText(getActivity().getApplicationContext(), "Login Failed Invalid Details entered Bro :(", Toast.LENGTH_LONG).show();
                }


            }
        });

    }


}

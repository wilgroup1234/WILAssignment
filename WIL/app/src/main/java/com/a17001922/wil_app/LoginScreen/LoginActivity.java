package com.a17001922.wil_app.LoginScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;


public class LoginActivity extends AppCompatActivity {
    Button btnLogin;
    TextView lblRegister;
    EditText et_email,et_password;
    LoginUserObject user;
    Connection con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = new LoginUserObject();
        con=new Connection();
    }

    @Override
    protected void onStart() {
        super.onStart();
        btnLogin=findViewById(R.id.btn_login);
        lblRegister =findViewById(R.id.txt_register);
        et_email=findViewById(R.id.et_userName);
        et_password=findViewById(R.id.et_Password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String password=et_password.getText().toString();
                user.setEmail(email);
                user.setPassword(password);
                String message="";
                if(con.userLogin(user)==false){
                    message="false";
                }else{
                    message="true";
                }
                Toast.makeText(LoginActivity.this,message , Toast.LENGTH_LONG).show();
            }
        });
        lblRegister.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                return false;
            }
        });


    }
}

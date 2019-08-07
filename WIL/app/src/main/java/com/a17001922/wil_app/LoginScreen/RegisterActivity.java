package com.a17001922.wil_app.LoginScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.a17001922.wil_app.Connection;
import com.a17001922.wil_app.R;

public class RegisterActivity extends AppCompatActivity {
    Button btnRegister,btnCancel;
    EditText et_firstName,et_surname,et_email,et_age,et_username,et_password,et_confirmPassword;
    RegisterUserObject user =new RegisterUserObject();
    Connection connection= new Connection();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_firstName = findViewById(R.id.et_registerFirstName);
        et_surname = findViewById(R.id.et_registerSurname);
        et_email=findViewById(R.id.et_registerEmail);
        et_age=findViewById(R.id.et_registerAge);
        et_password=findViewById(R.id.et_registerPassword);
        et_confirmPassword=findViewById(R.id.et_confirmPassword);
    }

    @Override
    protected void onStart() {
        //#TODO change register next screen from the login screen to the home page
        super.onStart();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,surname,email,password,confirmPassword="";
                name= et_firstName.getText().toString();
                surname=et_surname.getText().toString();
                int age = Integer.parseInt(et_age.getText().toString());
                email= et_email.getText().toString();
                password=et_password.getText().toString();
                confirmPassword=et_confirmPassword.getText().toString();

                user.setFirstName(name);
                user.setSurname(surname);
                user.setAge(age);
                user.setEmail(email);
                user.setPassword(password);
                user.setConfirmPassword(confirmPassword);

                connection.userRegister(user);


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);
            }
        });
    }
}

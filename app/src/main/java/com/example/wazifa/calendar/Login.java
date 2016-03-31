package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Login extends AppCompatActivity implements View.OnClickListener{


    Button btLogin;
    EditText etEmail, etPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);

        btLogin = (Button)findViewById(R.id.btLogin);
        btLogin.setOnClickListener(this);

        Firebase.setAndroidContext(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:



                break;
        }
    }
}

package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Signup extends AppCompatActivity implements View.OnClickListener {


    Button btRegister;
    EditText etName, etUserName, etPassword, etConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText)findViewById(R.id.etName);
        etUserName = (EditText)findViewById(R.id.etUserName);

        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);

        btRegister = (Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(this);
        Firebase.setAndroidContext(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btRegister:


                break;
        }
    }
}

package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class Signup extends AppCompatActivity  {


    Button btRegister;
    EditText etName, etEmail, etPassword, etConfirmPassword;
    DBmanager data;
    User usr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etName = (EditText)findViewById(R.id.etname);
        etEmail = (EditText)findViewById(R.id.etEmail);

        etPassword = (EditText)findViewById(R.id.etPassword);
        etConfirmPassword = (EditText)findViewById(R.id.etConfirmPassword);

        btRegister = (Button) findViewById(R.id.btRegister);

        Firebase.setAndroidContext(this);
        data= new DBmanager();
        usr = new User();
    }




    public void Signup(View v)
    {
        try
        {

            String email = etEmail.getText().toString();
            String name = etName.getText().toString();
            String password1=etPassword.getText().toString();
            String password2=etConfirmPassword.getText().toString();
            if(password1.equals(password2) && !email.isEmpty() && !name.isEmpty())
            {

                usr.setEmail(email);
                usr.setUsername(name);
                usr.setPassword(password1);
                data.putUser(usr);
                data.createAccount(usr.getEmail(), usr.getPassword());

                Intent next = new Intent(this,CalendarActivity.class);
                startActivity(next);

            }
        }catch(Exception ex){

        }
    }
}

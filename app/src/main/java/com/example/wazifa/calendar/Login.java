package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseException;

public class Login extends AppCompatActivity {


    Button btLogin;
    EditText etEmail, etPassword;
    DBmanager data;
    User usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPassword = (EditText)findViewById(R.id.etPassword);



        usr = new User();
        Firebase.setAndroidContext(this);
        data = new DBmanager();
    }

    public void login(View v)
    {
        try{
            String username=etEmail.getText().toString();
            usr.setUsername(username);
            usr=data.getUser(usr);
            String pass = etPassword.getText().toString();
            Intent next;
            next = new Intent(this,CalendarActivity.class);
            if(pass.equals(usr.getPassword()))
            {


                next.putExtra("user",usr);
                data.authUser(usr.getEmail(),usr.getPassword());
                startActivity(next);
            }





        }catch (FirebaseException ex){}
        System.out.println(usr.getPassword() +" "+usr.getEmail());
    }



}

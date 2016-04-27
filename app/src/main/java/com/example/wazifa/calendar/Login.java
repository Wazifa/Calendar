package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))return;
                usr.setUsername(s.toString());
                usr=data.getUser(usr);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etPassword = (EditText)findViewById(R.id.etPassword);



        usr = new User();
        Firebase.setAndroidContext(this);
        data = new DBmanager();
    }

    public void login(View v)
    {
        try{
            String username=etEmail.getText().toString();

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

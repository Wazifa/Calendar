package com.example.wazifa.calendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends Activity implements View.OnClickListener{

    Button btLogin, btSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLogin = (Button)findViewById(R.id.btLogin);
        btSignup = (Button)findViewById(R.id.btSignup);

        btLogin.setOnClickListener(this);
        btSignup.setOnClickListener(this);
        Firebase.setAndroidContext(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btLogin:
                Intent loginIntent = new Intent(this, Login.class);
                startActivity(loginIntent);

                break;

            case R.id.btSignup:
                //Intent signupIntent = new Intent(this, Signup.class);
                startActivity(new Intent(this, Signup.class));

                break;
        }
    }

}

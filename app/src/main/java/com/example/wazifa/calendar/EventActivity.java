package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import org.w3c.dom.Text;

public class EventActivity extends AppCompatActivity {

    EditText etEventTitle, etEventDescription, etLocation;
    Button btEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Firebase.setAndroidContext(this);


        etEventDescription = (EditText)findViewById(R.id.etEventDescription);
        etEventTitle = (EditText)findViewById(R.id.etEventTitle);
        etLocation = (EditText)findViewById(R.id.etLocation);

        btEvent = (Button)findViewById(R.id.btEvent);
    }

    public void addEvent(View v)
    {
        Intent intent = new Intent(this,Event.class);
        startActivity(intent);

    }

}

package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.firebase.client.Firebase;

public class EventActivity extends AppCompatActivity {

    EditText etEventTitle, etEventDescription, etLocation;
    DatePicker date;
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

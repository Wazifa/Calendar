package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class EventActivity extends AppCompatActivity {

    EditText etEventTitle, etEventDescription, etLocation;
    DatePicker date;
    Button btEvent;
    TimePicker time;
    User usr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Firebase.setAndroidContext(this);


        etEventDescription = (EditText)findViewById(R.id.etEventDescription);
        etEventTitle = (EditText)findViewById(R.id.etEventTitle);
        etLocation = (EditText)findViewById(R.id.etLocation);
        btEvent = (Button)findViewById(R.id.btEvent);

        date = (DatePicker)findViewById(R.id.date);
        time = (TimePicker)findViewById(R.id.time);
        Intent intent = getIntent();
        usr = (User)intent.getExtras().getSerializable("user");
    }

    public void addEvent(View v)
    {


        Event event = new Event();
        event.setTitle(etEventTitle.getText().toString());
        event.setLocation(etLocation.getText().toString());
        event.setTime(time.getCurrentHour().toString() + ":" + time.getCurrentMinute());
        event.setDate(date.getMonth() + "-" + date.getDayOfMonth() + "-" + date.getYear());



        DBmanager database  = new DBmanager();
        database.putEvent(usr, event);
        Toast.makeText(EventActivity.this,"Event Added", Toast.LENGTH_SHORT).show();
        this.finish();
    }

}

package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class EditActivity extends AppCompatActivity {
    private Event event;
    private User user;
    private DatePicker date;
    private TimePicker time;
    private EditText name, location;
    private String[] currentdate;
    private String[] currenttime;
    private DBmanager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);
        db = new DBmanager();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        event = (Event)getIntent().getSerializableExtra("event");
        user = (User)getIntent().getSerializableExtra("user");

        name = (EditText)findViewById(R.id.name_et);
        location = (EditText)findViewById(R.id.location_et);
        time = (TimePicker)findViewById(R.id.time);
        date = (DatePicker)findViewById(R.id.date);
        name.setText(event.getTitle());
        location.setText(event.getLocation());
        currentdate = event.getDate().split("-");
        currenttime = event.getTime().split(".");
        //time.setCurrentHour(Integer.parseInt(currenttime[0]));
        //time.setCurrentMinute(Integer.parseInt(currenttime[1]));
        int month = Integer.parseInt(currentdate[0])-1;
        int year = Integer.parseInt(currentdate[2]);
        int day = (Integer.parseInt(currentdate[1]));
        date.updateDate(year,month,day);


    }

    public void cancel(View v)
    {
        finish();

    }
    public void submit(View v)
    {
        db.removeEvent(user,event);
       String eventdate;
        String eventtime;
        eventdate = String.valueOf(date.getMonth()+1) +"-"+String.valueOf(date.getDayOfMonth()) + "-"+String.valueOf(date.getYear());
        eventtime = String.valueOf(time.getCurrentHour()) + "." +String.valueOf(time.getCurrentMinute());
        event.setDate(eventdate);
        event.setTime(eventtime);
        event.setTitle(name.getText().toString());
        event.setLocation(location.getText().toString());
        db.putEvent(user,event);
        Toast.makeText(this,"Event Updated!",Toast.LENGTH_SHORT).show();
        finish();
    }
}

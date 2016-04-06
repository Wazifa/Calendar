package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import com.firebase.client.Firebase;

/**
 * Created by Manny on 4/1/16.
 */
public class CalendarActivity extends AppCompatActivity
{

    // hello there
    private DBmanager database;
    private CalendarView calendar;
    private FrameLayout frame;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Firebase.setAndroidContext(this);
        database = new DBmanager();

        calendar = (CalendarView)findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                date=month+"-"+dayOfMonth+"-"+year;
                System.out.println(date);
            }
        });

        frame = (FrameLayout)findViewById(R.id.frame);



        //System.out.println();
    }

    public void getDate(View v)
    {
        System.out.println(calendar.getDate());
    }

    public void add_event(View v)
    {
        Intent next = new Intent(this,EventActivity.class);
        next.putExtra("Date",calendar.getDate());
        startActivity(next);
    }
}

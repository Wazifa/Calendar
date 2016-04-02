package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

/**
 * Created by Manny on 4/1/16.
 */
public class CalendarActivity extends AppCompatActivity
{
    private DBmanager database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //hello eddie
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Firebase.setAndroidContext(this);
        database = new DBmanager();
        //System.out.println();
    }
}

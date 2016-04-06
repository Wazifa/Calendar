package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class ListEventActivty extends AppCompatActivity
{
    private ListView elist;
    private User usr;
    private DBmanager database;
    private ArrayList<Event> eventList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event_activty);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Firebase.setAndroidContext(this);
        usr = (User)getIntent().getSerializableExtra("user");
        database = new DBmanager();
        elist = (ListView)findViewById(R.id.event_listview);
        eventList = new ArrayList<>();
        setTitle(usr.getUsername().toUpperCase()+" Events");



    }

    public void setup_list()
    {
        eventList = database.getAllEvents(usr);
        ListAdapter adapter = elist.getAdapter();

    }
}

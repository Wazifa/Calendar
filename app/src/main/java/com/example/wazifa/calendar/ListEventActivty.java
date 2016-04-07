package com.example.wazifa.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.List;

public class ListEventActivty extends Activity
{
    private ListView elist;
    private User usr;
    private DBmanager database;
    private List<Event> events;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {



        Firebase.setAndroidContext(this);

        usr = (User)getIntent().getSerializableExtra("user");

        database = new DBmanager();
        setup_list();

        setContentView(R.layout.activity_list_event_activty);

        elist = (ListView)findViewById(R.id.event_listview);

        super.onCreate(savedInstanceState);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        setTitle(usr.getUsername().toUpperCase() + " Events");

        ArrayAdapter<Event> adapter = new EventAdapter();
        elist.setAdapter(adapter);


    }

    private void setup_list()
    {
        System.err.println(usr.getUsername());
        events = database.getAllEvents(usr);

    }

    private class EventAdapter extends ArrayAdapter<Event>
    {
        public EventAdapter()
        {
            super(ListEventActivty.this, R.layout.event_view, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View item=convertView;

            if(item == null) item = getLayoutInflater().inflate(R.layout.event_view,parent,false);

            Event currentEvent =  events.get(position);

            TextView eventTitle = (TextView)item.findViewById(R.id.event_title);
            eventTitle.setText(currentEvent.getTitle());

            TextView eventDate = (TextView)item.findViewById(R.id.event_date);
            eventDate.setText(currentEvent.getDate());

            TextView eventTime = (TextView)item.findViewById(R.id.event_time);
            eventTime.setText(currentEvent.getTime());
            return item;
        }

    }

}

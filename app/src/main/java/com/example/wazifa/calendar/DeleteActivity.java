package com.example.wazifa.calendar;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.List;

public class DeleteActivity extends Activity {

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

        setContentView(R.layout.activity_delete);

        elist = (ListView)findViewById(R.id.del_listview);

        super.onCreate(savedInstanceState);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        //setSupportActionBar(toolbar);

        setTitle(usr.getUsername().toUpperCase() + " Events");

        ArrayAdapter<Event> adapter = new EventAdapter();

        elist.setAdapter(adapter);


    }
    public void dele(View v)  {
        EditText date = (EditText)findViewById(R.id.del_text);
        String datetxt=date.getText().toString();
        Event evee = new Event();
        evee.setDate(datetxt);
        database.removeEvent(usr, evee);
        Toast.makeText(DeleteActivity.this, datetxt + " removed!", Toast.LENGTH_SHORT);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
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
            super(DeleteActivity.this, R.layout.event_view, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            View item=convertView;

            if(item == null) item = getLayoutInflater().inflate(R.layout.event_view,parent,false);

            final Event currentEvent =  events.get(position);

            TextView eventTitle = (TextView)item.findViewById(R.id.event_title);
            eventTitle.setText(currentEvent.getTitle());

            TextView eventDate = (TextView)item.findViewById(R.id.event_date);
            eventDate.setText(currentEvent.getDate());

            TextView eventTime = (TextView)item.findViewById(R.id.event_time);
            eventTime.setText(currentEvent.getTime());

            eventTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.removeEvent(usr,currentEvent);
                    Toast.makeText(DeleteActivity.this,"Event Deleted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });

            return item;
        }



    }
}

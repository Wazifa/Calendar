package com.example.wazifa.calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.firebase.client.Firebase;

import java.util.List;

public class DeleteActivity extends AppCompatActivity {

    private Spinner spin;
    private DBmanager database;
    private User usr;
    private List<Event>events;
    private ArrayAdapter<Event>adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        usr = (User)getIntent().getSerializableExtra("user");
        Firebase.setAndroidContext(this);
        database = new DBmanager();
        events=database.getAllEvents(usr);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        spin = (Spinner)findViewById(R.id.del_spinner);
        adapter = new ArrayAdapter<Event>(this,android.R.layout.simple_spinner_dropdown_item,events);

        spin.setAdapter(adapter);




    }

    public class EventAdapter extends ArrayAdapter<Event>
    {
        public EventAdapter()
        {
         super(DeleteActivity.this,R.layout.activity_calendar,events);
        }


    }

    public void dele(View v)
    {
        Event eve = (Event)spin.getSelectedItem();
        System.err.print(eve);
        if(eve!=null)
        {
            database.removeEvent(usr,eve);
            finish();
        }
    }
}

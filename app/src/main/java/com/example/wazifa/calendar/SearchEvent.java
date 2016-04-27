package com.example.wazifa.calendar;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class SearchEvent extends Activity {
    private ListView elist;
    private User usr;
    private DBmanager database;
    private List<Event> events;



    //Eddie spaghetti or cowboy code here
    private EditText editText;
    //end of Eddie cowboy code


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(this);



        /*
        Eddie spa code
         */
        editText = (EditText) findViewById(R.id.textsearch);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (s.toString().equals("")){
                    return;
                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        usr = (User) getIntent().getSerializableExtra("user");

        database = new DBmanager();
        setup_list();
        setContentView(R.layout.activity_search_event);
        elist = (ListView) findViewById(R.id.searchListView);
        super.onCreate(savedInstanceState);

        //create array list here
      

        //call getallevents from db
        database.getAllEvents(usr);


        //iterate list to get event


        /*
        Eddie code
         */
        //get user data entry to search text field
        Intent intent = getIntent();
        if (Intent.ACTION_SEARCH.equals(intent.getAction())){
            String query = intent.getStringExtra(SearchManager.QUERY);
            DoSearch(query);
        }
        //end of search for event intent


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        setTitle(usr.getUsername().toUpperCase() + " Events");

        ArrayAdapter<Event> adapter = new EventAdapter();
        elist.setAdapter(adapter);



    }

    /*
    Eddie code
     */
    //search for event method
    public void DoSearch(String query) {
        EditText ev = (EditText) findViewById(R.id.textsearch);

        String evText = ev.getText().toString();
        Event evt = new Event();
        database.getEvent(usr, evt);
        finish();
    }
    //end of Do Search Method



    private void setup_list()
    {
        System.err.println(usr.getUsername());
        events = database.getAllEvents(usr);

    }




    public class EventAdapter extends ArrayAdapter<Event> {
        public EventAdapter() {
            super(SearchEvent.this, R.layout.event_view, events);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;

            if (item == null)
                item = getLayoutInflater().inflate(R.layout.event_view, parent, false);

            Event currentEvent = events.get(position);

            TextView eventTitle = (TextView) item.findViewById(R.id.event_title);
            eventTitle.setText(currentEvent.getTitle());

            TextView eventDate = (TextView) item.findViewById(R.id.event_date);
            eventDate.setText(currentEvent.getDate());

            TextView eventTime = (TextView) item.findViewById(R.id.event_time);
            eventTime.setText(currentEvent.getTime());
            return item;



        }

    }


}

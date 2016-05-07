package com.example.wazifa.calendar;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SearchEvent extends AppCompatActivity {
        ArrayList<String> listItems;
        ArrayAdapter<String> adapter;
        ListView listView;
        EditText editText;
        private String eventName;
        private Event eventSelected;
        private User usr;
        private DBmanager database;
        private ArrayList<Event> events;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Firebase.setAndroidContext(this);

            usr = (User)getIntent().getSerializableExtra("user");

            database = new DBmanager();

            events = database.getAllEvents(usr);

            //Creates blank event
            eventSelected = new Event();

            setContentView(R.layout.activity_search_event);

            listView=(ListView)findViewById(R.id.listview);

            editText=(EditText)findViewById(R.id.txtsearch);

            initList();

            editText.addTextChangedListener(new TextWatcher() {

                @Override

                public void beforeTextChanged(CharSequence s, int start, int count, int
                        after) {
                }

                @Override

                public void onTextChanged(CharSequence s, int start, int before, int
                        count) {
                    if (s.toString().equals("")) {
                        // reset listview
                        initList();
                    }
                    else{
                        // perform search
                        searchItem(s.toString());
                    }

                }


                @Override

                public void afterTextChanged(Editable s) {


                }


            });

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    eventName = (String) (parent.getItemAtPosition(position));

                    setContentView(R.layout.content_event_view);

                    for(int i = 0; i < events.size(); i++){
                        if(eventName.equals(events.get(i).getTitle())){
                            TextView eventTitle = (TextView)findViewById(R.id.event_title);
                            eventTitle.setText(events.get(i).getTitle());

                            TextView eventDate = (TextView)findViewById(R.id.event_date);
                            eventDate.setText(events.get(i).getDate());

                            TextView eventTime = (TextView)findViewById(R.id.event_time);
                            eventTime.setText(events.get(i).getTime());
                        }
                    }
                }
            });
        }

        public void searchItem(String textToSearch){
            for(int i = 0; i < listItems.size(); i++){

                if(!listItems.get(i).contains(textToSearch)){

                    listItems.remove(i);

                }

            }

            adapter.notifyDataSetChanged();

        }

        public void initList(){
            String[] items = new String[events.size()];

            for(int i = 0; i < events.size(); i++){
                items[i] = events.get(i).getTitle();
            }

            //transforms items from an array of strings into an arrayList of strings
            listItems = new ArrayList<>(Arrays.asList(items));

            adapter=new ArrayAdapter<String>(this,
                    R.layout.list_item, R.id.txtitem, listItems);

            listView.setAdapter(adapter);
        }
}


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
        String[] items;
        ArrayList<String> listItems;
        ArrayAdapter<String> adapter;
        ListView listView;
        EditText editText;
        ArrayList<String> databaseEvents;
        private ArrayList<Event> data;
        private ListView searchListView;
        private EditText textsearch;
        private String eventName;
        private Event eventSelected;
        private User usr;


        protected void onCreate(Bundle savedInstanceState) {

            //Creates blank event
            eventSelected = new Event();

            super.onCreate(savedInstanceState);

            //Creates string of all event titles
            databaseEvents = getIntent().getStringArrayListExtra("allEvents");

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

                    } else {

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
                    eventSelected.setTitle(eventName);


                    Intent eventActivity = new Intent(view.getContext(), EventActivity.class);
                    eventActivity.putExtra("selectedevent", eventSelected);
                    startActivity(eventActivity);
                }
            });

        }




        public void searchItem(String textToSearch){
            for(String item:listItems){

                if(!item.contains(textToSearch)){

                    listItems.remove(item);

                }

            }

            adapter.notifyDataSetChanged();

        }

        public void initList(){

            String eventString = databaseEvents.toString();

            //Cleans databaseEvents.toString() and stores into items
            //EX: Before: [Apple, Orange, Banana]
            //    After: Apple
            //           Orange
            //           Banana
            eventString = eventString.replaceAll("[\\[\\](){}]"," ");
            String[] items = eventString.split("[,]");
            for(int i = 0; i < items.length; i++){
                items[i] = items[i].substring(1);
                System.out.println(items[i]);
            }

            listItems = new ArrayList<>(Arrays.asList(items));

            adapter=new ArrayAdapter<String>(this,
                    R.layout.list_item, R.id.txtitem, listItems);

            listView.setAdapter(adapter);

        }



}


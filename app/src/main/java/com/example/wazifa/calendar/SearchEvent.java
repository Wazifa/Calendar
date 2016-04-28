package com.example.wazifa.calendar;

import android.app.Activity;
import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
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

public class SearchEvent extends AppCompatActivity {

    private String[] items;
    private ArrayList<String> listItems;
    private ArrayList<String> databaseEvents;
    private ArrayAdapter<String> adapter;
    private ArrayList<Event> data;
    private ListView searchListView;
    private EditText textsearch;
    private String eventName;
    private Event eventSelected;


    protected void OnCreate (Bundle savedInstanceState){

        eventSelected = new Event();


        super.onCreate(savedInstanceState);


        databaseEvents = getIntent().getStringArrayListExtra("allEvents");
        setContentView(R.layout.activity_search_event);

        searchListView = (ListView) findViewById(R.id.searchListView);
        textsearch = (EditText) findViewById(R.id.textsearch);
        initList();

        textsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals("")){
                    //reset list view
                    initList();
                }
                else {
                    //do search
                    searchItem(s.toString());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        //problem might be here -- > line 4 with setTitle or line 5 with EventActivity.class
        searchListView.setOnItemClickListener (new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                eventName = (String) (parent.getItemAtPosition(position));
                eventSelected.setTitle(eventName);


                Intent eventActivity = new Intent(view.getContext(), SearchEvent.class);
                eventActivity.putExtra("selectedevent",  eventSelected);
                startActivity(eventActivity);
            }
        });



    }

    public void searchItem (String textToSearch){
        textToSearch = textToSearch.substring(0, 1).toUpperCase() + textToSearch.substring(1);
        for (String item: items){
            if (!item.contains(textToSearch)){
                listItems.remove(item);
            }

        }
        adapter.notifyDataSetChanged();
    }

    public void initList (){
        items = new String[databaseEvents.size()];
        items = databaseEvents.toArray(items);
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<>(this, R.layout.list_item, R.id.txtitem, listItems);
        searchListView.setAdapter(adapter);

    }


}

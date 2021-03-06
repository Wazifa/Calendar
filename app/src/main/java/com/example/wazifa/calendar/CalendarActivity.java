package com.example.wazifa.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.FrameLayout;

import com.firebase.client.Firebase;

import java.util.ArrayList;

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
    private User usr;
    private ArrayList<Event> allEvents;
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
        Intent intent = getIntent();
        usr=(User)intent.getSerializableExtra("user");
        allEvents = database.getAllEvents(usr);
        setTitle(usr.getUsername().toUpperCase()+ " CALENDAR");

        frame = (FrameLayout)findViewById(R.id.frame);




        //System.out.println();
    }

    public void getDate(View v)
    {
        System.out.println(calendar.getDate());
    }

    public void list_event()
    {
        Intent next = new Intent(this,ListEventActivty.class);
        next.putExtra("user",usr);
        startActivity(next);
    }

    public void add_event(View v)
    {
        Intent next = new Intent(this,EventActivity.class);
        next.putExtra("user",usr);
        next.putExtra("Date",calendar.getDate());
        startActivity(next);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_add:     add_event(getCurrentFocus());return true;
            case R.id.menu_view:    list_event();return true;
            case R.id.menu_del:      del_event();return true;
            case R.id.menu_share:    share_even();return true;
            case R.id.menu_search:   searchEvent();return true;
            case R.id.menu_up: edit_event();return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    private void share_even()
    {
        Intent next = new Intent(this,ShareActivity.class);
        next.putExtra("user",usr);
        startActivity(next);
    }
    private void del_event()
    {
        Intent next = new Intent(this,DeleteActivity.class);
        next.putExtra("user",usr);
        startActivity(next);
    }
    private void edit_event()
    {
        Intent next = new Intent(this,EditListviewActivity.class);
        next.putExtra("user",usr);
        startActivity(next);
    }

    //SearchEvent Eddie

    private void searchEvent(){
        //TODO
        Intent next = new Intent(this, SearchEvent.class);
        next.putExtra("user", usr);
        //next.putExtra("allEvents",allEvents);
        startActivity(next);
    }



}


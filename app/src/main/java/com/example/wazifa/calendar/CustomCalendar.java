package com.example.wazifa.calendar;

import android.content.Context;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Manny on 4/5/16.
 */
public class CustomCalendar extends LinearLayout
{
    private TextView tvMonth;
    private GridView calendar_grid;
    private RelativeLayout head;
    private ImageView btLeft,btRight;

    public CustomCalendar(Context contex){
        super(contex);
        intit();
    }
    private void intit()
    {
        tvMonth=(TextView)findViewById(R.id.tvMonth);
        calendar_grid = (GridView)findViewById(R.id.calendar_grid);
        head= (RelativeLayout)findViewById(R.id.head_relative);
        btLeft=(ImageView)findViewById(R.id.ivleft);
        btRight=(ImageView)findViewById(R.id.ivRight);
    }
}

package com.example.wazifa.calendar;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Wazifa on 4/7/2016.
 */
public class Share extends Activity{


    public void sth()
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_TEXT , "Dear .....");
        startActivity(Intent.createChooser(email, "Choose an Email client :"));
    }
}
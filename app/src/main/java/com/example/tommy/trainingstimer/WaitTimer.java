package com.example.tommy.trainingstimer;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Tommy on 15.07.2016.
 */
public class WaitTimer extends IntentService {


    public WaitTimer(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long in= intent.getIntExtra("Time", 0);
        Log.v("Waittime", "Now we wait: "+ in);
        try {
            wait(in);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.example.tommy.trainingstimer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by Tommy on 14.07.2016.
 */
public class AdapterTimerView extends BaseAdapter {

    private LinkedList<Timer> uebung;
    private LinkedList<Timer> temp;
    private Context context;
    private TextView timerText;
    private CountDownTimer countDownTimer;
    public AdapterTimerView(Context con, LinkedList<Timer> ueb){
        this.context= con;
        this.uebung=ueb;
    }

    @Override
    public int getCount() {
        return uebung.size();
    }

    @Override
    public Object getItem(int position) {
        return uebung.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.adapter_timer_list, null);
        }
        uebung.get(position).textView= (TextView)v.findViewById(R.id.timerText);
        uebung.get(position).progressBar=(ProgressBar)v.findViewById((R.id.progressBar));
        notifyDataSetChanged();




        return v;
    }
    public void startTimer(Activity con){


                        for(Timer t:uebung){
                            t.context=con;
                            Log.v("Timer seconds","sind in der Schleife "+t.seconds+" "+t.finish);
                        }
                        int index = uebung.indexOf(uebung.getFirst());


                                uebung.get(index).start();


                        //do
                        for(Timer c:uebung){
                            while (uebung.get(index).finish != true); {

                                if (uebung.get(index).finish == true) {

                                    for(Timer f:uebung) {
                                        Log.v("Finish", "ist true " + uebung.get(index).finish);
                                        Log.v("Index", "Index: " + index);
                                        //
                                        if (uebung.get(index).finish == true&&index!=uebung.size()-1) {
                                            index++;

                                                    uebung.get(index).start();


                                            Log.v("Timer seconds","sind in der for Schleife "+f.seconds+" "+f.context);
                                        }
                                    }// while (index != uebung.size() - 1);
                                }
                            }
                        }
                        //while (index != uebung.size() -1);
                        Log.v("Fertig", "Schleife fertig");
                        for(Timer r:uebung){
                            r.finish=false;
                            Log.v("Finish", "Wert wurdde auf:  " + r.finish);
                        }


                    }












}

package com.example.tommy.trainingstimer;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tommy on 18.07.2016.
 */
public class AdapterNewTimer extends BaseAdapter {
    private LinkedList<Timer> uebung;
    private LinkedList<Timer> temp;
    private Context context;
    private TextView timerText;
    private Button plus;
    private Button minus;
    private TextView plusText;
    private View v;

    private int secondss;
    private CountDownTimer countDownTimer;
    public AdapterNewTimer(Context con, LinkedList<Timer> ueb){
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        v = convertView;
        ViewHolder mainViewholder;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.adapter_newtimer_list, null);
            final ViewHolder viewHolder= new ViewHolder();
            viewHolder.btnMinus= (Button)v.findViewById(R.id.minusBUtton);
            viewHolder.btnPlus=(Button)v.findViewById(R.id.plusButton);
            viewHolder.txtSeconds=(TextView)v.findViewById((R.id.secondsText));
            viewHolder.txtSeconds.setText(""+String.format("%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes( uebung.get(position).seconds*1000),
                    TimeUnit.MILLISECONDS.toSeconds(uebung.get(position).seconds*1000) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uebung.get(position).seconds*1000))));

            notifyDataSetChanged();
            viewHolder.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("Timer Seconds","Ist klickt plus "+position);

                    uebung.get(position).seconds++;
                    Log.v("Timer Seconds","Sekunden sind "+uebung.get(position).seconds);
                    viewHolder.txtSeconds.setText(""+String.format("%d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes( uebung.get(position).seconds*1000),
                            TimeUnit.MILLISECONDS.toSeconds(uebung.get(position).seconds*1000) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uebung.get(position).seconds*1000))));
                    notifyDataSetChanged();
                }
            });

            viewHolder.btnMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.v("Timer Seconds","Ist klickt minus "+position);
                    if(uebung.get(position).seconds!=0){
                    uebung.get(position).seconds--;
                    viewHolder.txtSeconds.setText(""+String.format("%d:%02d",
                            TimeUnit.MILLISECONDS.toMinutes( uebung.get(position).seconds*1000),
                            TimeUnit.MILLISECONDS.toSeconds(uebung.get(position).seconds*1000) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(uebung.get(position).seconds*1000))));
                    notifyDataSetChanged();}
                }
            });
            v.setTag(viewHolder);
        }
/*        else{
            mainViewholder=(ViewHolder)v.getTag();
            mainViewholder.txtSeconds.setText(""+0);
        }*/












        return v;
    }
    public void saveDate(){
        ListTimerHelper lH=new ListTimerHelper();
        lH.setLinkedList(new LinkedList<Timer>());
        for (Timer t:uebung){
            //lösung für Befüllen mit Konstruktor
            Timer newT= new Timer(t.seconds*1000);
            lH.getLinkedList().add(newT);
        }
        HelperClass.getHelperList().add(lH);
        for(ListTimerHelper l:HelperClass.getHelperList()){
            for(Timer t:l.getLinkedList()){
                Log.v("Timer Seconds","sind "+t.seconds);
            }
        }
    }



    public class ViewHolder{
        Button btnPlus;
        Button btnMinus;
        TextView txtSeconds;

    }
}

package com.example.tommy.trainingstimer;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Tommy on 14.07.2016.
 */
public class Timer extends CountDownTimer implements Serializable{
    TextView textView;
    ProgressBar progressBar;
    boolean finish= false;
    private String nameUebung;
    Activity context;
    int i=0;
    MediaPlayer mediaPlayer;


    public TextView getTextView() {
        return textView;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    int seconds;
    public Timer(){super(0,1000);}
    public Timer(int sec){
        super(sec, 1000);
        this.seconds=sec;

    }
    public Timer(int sec, Activity con){
        super(sec, 1000);
        this.seconds=sec;
        this.context=con;
        mediaPlayer= new MediaPlayer();
        mediaPlayer.create(context, R.raw.piep);
    }

    public Timer(String nameUebung, int seconds) {
        super(seconds, 1000);
        this.nameUebung = nameUebung;
    }

    @Override
    public void onTick(long l) {
        //TODO über diese Klasse muss der sound eingefügt werden



                textView.setText(""+String.format("%d:%02d",
                        TimeUnit.MILLISECONDS.toMinutes( l),
                        TimeUnit.MILLISECONDS.toSeconds(l) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l))));

                Log.v("Timer seconds","Sind im Timer "+l/1000);
                int progress = (int) (l/1000);
                    progressBar.setMax(seconds/1000);

            if (l/1000==3) {
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer player) {
                        player.start();
                    }
                });
                Log.v("Piep", "Piep ist 3");
                mediaPlayer.reset();
            }
            if  (l/1000==2){
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer player) {
                        player.start();
                    }
                });
                Log.v("Piep", "Piep ist 2");
                mediaPlayer.reset();
            }
            if (l/1000==1) {
                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    public void onPrepared(MediaPlayer player) {
                        player.start();
                    }
                });
                Log.v("Piep", "Piep ist 1");
                mediaPlayer.reset();
                mediaPlayer.release();
            }




       // progressBar.getMax()-progress+1
                progressBar.setProgress(progress);





    }

    @Override
    public void onFinish() {
       isFinish();
        progressBar.setProgress(i);
                textView.setText("Verbleibende Zeit: "+ 0);






    }
    public void isFinish(){
        finish= true;

    }
    public String getNameUebung() {
        return nameUebung;
    }

    public void setNameUebung(String nameUebung) {
        this.nameUebung = nameUebung;
    }
}

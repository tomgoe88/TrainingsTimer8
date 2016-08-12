package com.example.tommy.trainingstimer;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTimer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTimer extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinkedList<Timer> timers;
    LinkedList<Timer> temptimer;



    ListView listView;
    Thread newThread;
    AdapterTimerView adapterTimerView;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public FragmentTimer() {
        // Required empty public constructor
    }

    public FragmentTimer(LinkedList<Timer> timers) {
        this.timers = timers;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentTimer.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentTimer newInstance(String param1, String param2) {
        FragmentTimer fragment = new FragmentTimer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if(savedInstanceState!=null){

            temptimer=(LinkedList<Timer>)savedInstanceState.getSerializable("LIST_TIMER");

           adapterTimerView=new AdapterTimerView(getContext(),temptimer);

        }
        else {

           adapterTimerView = new AdapterTimerView(getContext(), timers);
       }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
/*        timers= new LinkedList<Timer>();
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(5000));*/
        setRetainInstance(true);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(temptimer== null ){
        temptimer=new LinkedList<Timer>();
        for(Timer t:timers){
            temptimer.add(new Timer(t.seconds,this.getActivity()));
        }}
        setRetainInstance(true);
        adapterTimerView=new AdapterTimerView(getContext(),temptimer);
        View v= inflater.inflate(R.layout.fragment_fragment_timer, container, false);
        listView=(ListView)v.findViewById(R.id.timerList);
        Button startTimer=(Button)v.findViewById(R.id.startTimer);

        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        Runnable newRun= new Runnable() {
                            @Override
                            public void run() {
                                adapterTimerView.startTimer(getActivity());
                                Log.v("Thread","gestartet"+getContext().toString());
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        uiThread();
                                    }
                                });
/*                      FragmentTransaction fT= getActivity().getSupportFragmentManager().beginTransaction();
                        fT.replace(R.id.pagerFragment,new FragmentPager());
                        fT.commit();*/
                            }
                        };
                Thread newThread= new Thread(newRun);
                newThread.start();




                    }



        });
        listView.setAdapter(adapterTimerView);
        return v;
    }
    public void uiThread(){
        listView.setAdapter(null);
        adapterTimerView= new AdapterTimerView(getActivity(),temptimer);
        adapterTimerView.notifyDataSetChanged();
        listView.setAdapter(adapterTimerView);


    }
    public LinkedList<Timer> getTimers() {
        return timers;
    }

    public void setTimers(LinkedList<Timer> timers) {
        this.timers = timers;
    }

    @Override
    public String toString() {
        return "FragmentTimer";
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("LIST_TIMER", temptimer);
    }
}

package com.example.tommy.trainingstimer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPager.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPager#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPager extends Fragment implements ViewPager.OnPageChangeListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private PagerTimer pagerTimer;
    private List<Fragment> fragments= new ArrayList<Fragment>();
    LinkedList<Timer> timers;
    ViewPager viewPager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPager() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPager.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPager newInstance(String param1, String param2) {
        FragmentPager fragment = new FragmentPager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        Log.v("List", "Größe "+ fragments.size());
        for(ListTimerHelper h:HelperClass.getHelperList()){
            fragments.add(new FragmentTimer(h.getLinkedList()));
        }
        setRetainInstance(true);

/*        timers= new LinkedList<Timer>();
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(2000));
        timers.add(new Timer(5000));*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_fragment_pager, container, false);

        pagerTimer= new PagerTimer(getChildFragmentManager(),fragments);
        viewPager=(ViewPager) v.findViewById(R.id.timerPager);
        viewPager.setAdapter(pagerTimer);// Inflate the layout for this fragment
        viewPager.addOnPageChangeListener(this);
//Wird nicht mehr benötigt, geht jetzt über den Drawer
/*        Button button=(Button) v.findViewById(R.id.newFragment);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //hier muss jetzt noch die onResultMethode eingefügt werden
                FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.pagerFragment,new FragmentNewTimer());
                ft.addToBackStack("PagerF");
                ft.commit();
//            Intent intent=new Intent(MainActivity.this, MainActivity.class);
//            startActivity(intent);

                pagerTimer.notifyDataSetChanged();

            }
        });*/
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("onActivityResult", "requestCode = " + requestCode);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    @Override
    public void onPageSelected(int position) {

        Fragment fragment = pagerTimer.getFragment(position);
        if (fragment != null) {
            fragment.onResume();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

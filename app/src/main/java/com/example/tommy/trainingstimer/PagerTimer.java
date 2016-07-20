package com.example.tommy.trainingstimer;





import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy on 10.07.2016.
 */
public class PagerTimer extends FragmentPagerAdapter {
    private String[] titles;
    public  List<Fragment> fragments = new ArrayList<Fragment>();
    int fcount;
    FragmentManager fMM;


    public PagerTimer(FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fMM=fm;

        this.fragments= fragmentList;
       // this.titles= title;
        this.fcount= fragments.size();
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fcount;
    }

    public void AddFragment(Fragment fragment)
    {
        fragments.add(fragment);
        notifyDataSetChanged();
    }
   // @Override
  //  public CharSequence getPageTitle(int position) {
    //    String tabtitle= titles[position];
   //     return tabtitle;
   // }


    public Fragment getFragment(int position) {
        Fragment fragment = null;
        fragment = fragments.get(position);
        if (fragment != null) {
            fragment = fragments.get(position);
        }
        return fragment;
    }

}


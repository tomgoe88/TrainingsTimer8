package com.example.tommy.trainingstimer;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tommy on 10.07.2016.
 */
public class HelperClass {
    public static List<Fragment> fragmentList= new ArrayList<Fragment>();

    public static List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public static void setFragmentList(List<Fragment> fragmentList) {
        HelperClass.fragmentList = fragmentList;
    }
}


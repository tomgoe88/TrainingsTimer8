package com.example.tommy.trainingstimer;

import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Tommy on 10.07.2016.
 */
public class HelperClass {
    public static List<Fragment> fragmentList= new ArrayList<Fragment>();
    private static LinkedList<ListTimerHelper> helperList= new LinkedList<ListTimerHelper>();

    public static LinkedList<ListTimerHelper> getHelperList() {
        return helperList;
    }

    public static void setHelperList(LinkedList<ListTimerHelper> helperList) {
        HelperClass.helperList = helperList;
    }

    public static List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public static void setFragmentList(List<Fragment> fragmentList) {
        HelperClass.fragmentList = fragmentList;
    }

}


package com.example.tommy.trainingstimer;

import java.util.LinkedList;

/**
 * Created by Tommy on 19.07.2016.
 */
public class ListTimerHelper {
    public LinkedList<Timer> getLinkedList() {
        return linkedList;
    }

    public void setLinkedList(LinkedList<Timer> linkedList) {
        this.linkedList = linkedList;
    }

    private LinkedList<Timer> linkedList;

}

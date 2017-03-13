package com.team.speedcoders.hotelmanagement.OrederListActivity;

import com.team.speedcoders.hotelmanagement.ItemList.OrderedItem;

import java.util.ArrayList;

/**
 * Created by Sakkar on 3/12/2017.
 */

public class Orders {
    private boolean accepted=false;
    private String orderGenerator;
    private ArrayList<OrderedItem> orderedItemArrayList;

    public Orders(String orderGenerator, ArrayList<OrderedItem> orderedItemArrayList) {
        this.orderGenerator = orderGenerator;
        this.orderedItemArrayList = orderedItemArrayList;
    }

    public void setOrderedItemArrayList(ArrayList<OrderedItem> orderedItemArrayList) {
        this.orderedItemArrayList.addAll(orderedItemArrayList);
    }

    public String getOrderGenerator() {
        return orderGenerator;
    }

    public ArrayList<OrderedItem> getOrderedItemArrayList() {
        return orderedItemArrayList;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isAccepted() {
        return accepted;
    }
}

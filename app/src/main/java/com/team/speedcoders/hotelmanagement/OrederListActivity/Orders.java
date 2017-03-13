package com.team.speedcoders.hotelmanagement.OrederListActivity;

import com.team.speedcoders.hotelmanagement.ItemList.OrderedItem;

import java.util.ArrayList;

/**
 * Created by Sakkar on 3/12/2017.
 */

public class Orders {
    String orderGenerator;
    ArrayList<OrderedItem> orderedItemArrayList;

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
}

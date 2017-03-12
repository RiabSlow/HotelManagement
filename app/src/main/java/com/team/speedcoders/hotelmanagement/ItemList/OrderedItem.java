package com.team.speedcoders.hotelmanagement.ItemList;

/**
 * Created by Sakkar on 3/12/2017.
 */

public class OrderedItem {
    private String name;
    private int quantity;

    public OrderedItem() {
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

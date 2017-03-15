package com.team.speedcoders.hotelmanagement.OrederListActivity;

import com.team.speedcoders.hotelmanagement.ItemList.OrderedItem;


class ServedOrder {

    private Orders orders;
    private String time;

    ServedOrder(Orders orders, String time) {
        this.orders = orders;
        this.time = time;
    }

    public ServedOrder() {
    }

    public Orders getOrderedItem() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

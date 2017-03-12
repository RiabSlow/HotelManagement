package com.team.speedcoders.hotelmanagement.ItemList;



class Items {
    private float price;
    private String food_name;
    private int quantity=0;
    private int imageId;

    Items(float price, String food_name,int imageId) {
        this.price = price;
        this.food_name = food_name;
        this.imageId=imageId;
    }

    int getImageId() {
        return imageId;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    float getPrice() {
        return price;
    }

    String getFood_name() {
        return food_name;
    }

    int getQuantity() {
        return quantity;
    }
}

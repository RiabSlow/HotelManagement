package com.team.speedcoders.hotelmanagement.ItemList;

import com.team.speedcoders.hotelmanagement.R;

import java.util.ArrayList;


class Foods {
    private ArrayList<Items> arrayList=new ArrayList<>();
    public Foods(ArrayList<Items> arrayList) {
        this.arrayList = arrayList;
    }
    private final static Foods foods=new Foods();

     Foods() {
        arrayList.add(new Items(100,"American Cho suye", R.drawable.rsz_american_chosuye));
        arrayList.add(new Items(100,"Chicken tacos",R.drawable.rsz_chicken_taco));
        arrayList.add(new Items(100,"Chicken Noodls",R.drawable.rsz_chicken_noodls));
        arrayList.add(new Items(100,"Egg Noodls",R.drawable.rsz_egg_noodls));
        arrayList.add(new Items(100,"Shawarma",R.drawable.rsz_shawarma));
        arrayList.add(new Items(100,"Vegitable Noodls",R.drawable.rsz_vagitble_noodls));
        arrayList.add(new Items(100,"Chicken parota",R.drawable.rsz_chicken_paratha));
    }

    static Foods getInstent(){
        return foods;
    }

    void setQuantity(int pos,int n){
        arrayList.get(pos).setQuantity(n);
    }

    Items getFoodAt(int i){
        return arrayList.get(i);
    }

    int numberOfItems(){
        return arrayList.size();
    }
}

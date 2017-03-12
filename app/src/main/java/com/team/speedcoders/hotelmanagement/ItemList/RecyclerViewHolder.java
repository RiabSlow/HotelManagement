package com.team.speedcoders.hotelmanagement.ItemList;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.speedcoders.hotelmanagement.R;

/**
 * Created by Sakkar on 3/11/2017.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView name,price,quantity;
    ImageView image;
    OnItemSelect onItemSelect;

    RecyclerViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        name= (TextView) itemView.findViewById(R.id.foodName);
        price= (TextView) itemView.findViewById(R.id.price);
        quantity= (TextView) itemView.findViewById(R.id.itemNumber);
        image= (ImageView) itemView.findViewById(R.id.foodImage);
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setPrice(float price) {
        String s=price+" Tk";
        this.price.setText(s);
    }

    public void setQuantity(int quantity) {
        String s=""+quantity;
        this.quantity.setText(s);
        if(quantity==0)
            this.quantity.setVisibility(View.GONE);
        else this.quantity.setVisibility(View.VISIBLE);
    }

    public void setImage(int image) {
        this.image.setImageResource(image);
    }

    public void setOnItemSelect(OnItemSelect onItemSelect) {
        this.onItemSelect = onItemSelect;
    }

    @Override
    public void onClick(View v) {
        onItemSelect.itemSelect(getAdapterPosition());
    }

    interface OnItemSelect{
        void itemSelect(int i);
    }
}

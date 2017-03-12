package com.team.speedcoders.hotelmanagement.ItemList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.team.speedcoders.hotelmanagement.R;


class ItemListRecyclerViewAdapte extends RecyclerView.Adapter<RecyclerViewHolder> {

    private Foods foods;
    private Context context;
    RecyclerViewHolder.OnItemSelect onItemSelect;

    ItemListRecyclerViewAdapte(Foods foods, Context context) {
        this.foods = foods;
        this.context = context;
    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.row_design, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        holder.setImage(foods.getFoodAt(position).getImageId());
        holder.setName(foods.getFoodAt(position).getFood_name());
        holder.setPrice(foods.getFoodAt(position).getPrice());
        holder.setQuantity(foods.getFoodAt(position).getQuantity());
        holder.setOnItemSelect(onItemSelect);
    }

    @Override
    public int getItemCount() {
        return foods.numberOfItems();
    }

    public void setListener(RecyclerViewHolder.OnItemSelect onItemSelect) {
        this.onItemSelect=onItemSelect;
    }
}

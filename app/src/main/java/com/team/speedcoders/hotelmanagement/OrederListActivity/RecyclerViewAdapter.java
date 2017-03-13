package com.team.speedcoders.hotelmanagement.OrederListActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.team.speedcoders.hotelmanagement.ItemList.OrderedItem;
import com.team.speedcoders.hotelmanagement.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Sakkar on 3/12/2017.
 */

class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    ArrayList<Orders> list=new ArrayList<>();

    public RecyclerViewAdapter(Context context,ArrayList<Orders> list) {
        this.context = context;
        inflater=LayoutInflater.from(context);
        this.list = list;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.order_row_design,parent,false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setTableName(list.get(position).getOrderGenerator());
        ArrayList<OrderedItem> arrayList=list.get(position).getOrderedItemArrayList();
        String s="";
        for(int i=0;i<arrayList.size();i++){
            s+=arrayList.get(i).getQuantity()+"X "+arrayList.get(i).getName()+"\n";
        }
        holder.setOrders(s);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tableName,orders;
        public ViewHolder(View itemView) {
            super(itemView);
            tableName= (TextView) itemView.findViewById(R.id.orderTableNo);
            orders= (TextView) itemView.findViewById(R.id.orderDetail);
        }

        public void setTableName(String name) {
            tableName.setText(name);
        }
        public void setOrders(String name) {
            orders.setText(name);
        }
    }
}

package com.team.speedcoders.hotelmanagement.ItemList;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.team.speedcoders.hotelmanagement.R;

public class DetailFragment extends DialogFragment {

    Foods foods;
    int value,i;
    TextView price,name,totalPrice,cancle;
    Button buyNow;
    ImageView image;
    Items item;
    RecyclerView recyclerView;
    DialogInterface dialogInterface;

    public DetailFragment() {
        // Required empty public constructor
        foods=Foods.getInstent();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCancelable(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_detail, container, false);
        image= (ImageView) view.findViewById(R.id.detailFoodImage);
        name= (TextView) view.findViewById(R.id.detailName);
        price= (TextView) view.findViewById(R.id.detailPrice);
        buyNow= (Button) view.findViewById(R.id.buyNow);
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogInterface.cllicked(value,i);
                dismiss();
            }
        });
        totalPrice= (TextView) view.findViewById(R.id.detailTotalPrice);
        recyclerView= (RecyclerView) view.findViewById(R.id.detailResView);
        cancle= (TextView) view.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        image.setImageResource(item.getImageId());
        name.setText(item.getFood_name());
        price.setText(item.getPrice()+" Tk");
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RecyclerView.Adapter<ViewHolder>() {
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ViewHolder(LayoutInflater.from(getContext()).inflate(R.layout.circuler_text_view,parent,false));
            }

            @Override
            public void onBindViewHolder(ViewHolder holder, int position) {
                holder.setPos(position);
            }

            @Override
            public int getItemCount() {
                return 9;
            }
        });
    }


    public void detailOf(int i) {
        this.i=i;
        item=foods.getFoodAt(i);
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ViewHolder(View itemView) {
            super(itemView);
        }

        void setPos(int pos){
            String position=""+pos;
            ((TextView)itemView).setText(position);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            value=getAdapterPosition();
            float total=item.getPrice()*value;
            String totalPri="Total = "+total+" Tk";
            totalPrice.setText(totalPri);
        }
    }

    void setClickListener(DialogInterface dialogInterface){
        this.dialogInterface=dialogInterface;
    }

    interface DialogInterface {
        void cllicked(int n,int i);
    }

}

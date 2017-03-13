package com.team.speedcoders.hotelmanagement.ItemList;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.team.speedcoders.hotelmanagement.R;

import java.util.ArrayList;

public class ItemsLIst extends AppCompatActivity implements RecyclerViewHolder.OnItemSelect, DetailFragment.DialogInterface {

    ItemListRecyclerViewAdapte recyclerViewAdapte;
    Foods foods;
    RecyclerView recyclerView;
    DetailFragment detailFragment;
    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener stateListener;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items_list);
        if (savedInstanceState != null)
            return;
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Items");

        foods = new Foods();
        recyclerView = ((RecyclerView) findViewById(R.id.recycler_view));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapte = new ItemListRecyclerViewAdapte(foods, this);
        recyclerViewAdapte.setListener(this);
        recyclerView.setAdapter(recyclerViewAdapte);

        auth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    finish();
                    Toast.makeText(ItemsLIst.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        database = FirebaseDatabase.getInstance();
        if (auth.getCurrentUser() != null)
            name = auth.getCurrentUser().getDisplayName();
        reference = database.getReference("orders/"+name);

        auth.addAuthStateListener(stateListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.over_flow_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.order:
                float totalPrice = 0;
                String msg = "";
                final ArrayList<OrderedItem> list = new ArrayList<>();
                for (int i = 0; i < foods.numberOfItems(); i++) {
                    int quantity = foods.getFoodAt(i).getQuantity();
                    if (quantity > 0) {
                        OrderedItem orderedItem = new OrderedItem();
                        orderedItem.setName(foods.getFoodAt(i).getFood_name());
                        orderedItem.setQuantity(quantity);
                        list.add(orderedItem);
                        totalPrice += foods.getFoodAt(i).getPrice() * quantity;
                        msg += quantity + "x " + foods.getFoodAt(i).getFood_name() + "\n";
                    }
                }
                if (totalPrice == 0) {
                    Toast.makeText(this, "No item selected...", Toast.LENGTH_LONG).show();
                } else {
                    AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                    dialog.setTitle("Total price " + totalPrice + "Tk");
                    dialog.setCancelable(false);
                    dialog.setMessage(msg);
                    dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            reference.setValue(list);

                            for (int i = 0; i < foods.numberOfItems(); i++) {
                                foods.getFoodAt(i).setQuantity(0);
                            }
                            recyclerViewAdapte.notifyDataSetChanged();
                            Toast.makeText(ItemsLIst.this, "You will be served as quickly as possible", Toast.LENGTH_LONG).show();
                        }
                    }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(ItemsLIst.this, "You can change the order list", Toast.LENGTH_LONG).show();
                        }
                    }).create().show();
                }
                break;
            case R.id.logout:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("Click confirm to logout");
                dialog.setCancelable(false);
                dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        auth.signOut();
                    }
                }).setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).create().show();
                break;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        auth.removeAuthStateListener(stateListener);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finish();
    }

    @Override
    public void itemSelect(int i) {
        detailFragment = new DetailFragment();
        detailFragment.detailOf(i);
        detailFragment.setClickListener(this);
        detailFragment.show(getSupportFragmentManager(), "show");
    }

    @Override
    public void cllicked(int n, int i) {
        foods.getFoodAt(i).setQuantity(n);
        recyclerViewAdapte.notifyItemChanged(i);
    }
}

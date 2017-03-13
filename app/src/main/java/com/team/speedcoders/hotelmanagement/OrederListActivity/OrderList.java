package com.team.speedcoders.hotelmanagement.OrederListActivity;

import android.content.Context;
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
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.team.speedcoders.hotelmanagement.ItemList.OrderedItem;
import com.team.speedcoders.hotelmanagement.LogInActivity.LoginActivity;
import com.team.speedcoders.hotelmanagement.R;

import java.util.ArrayList;

public class OrderList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    FirebaseDatabase database;
    DatabaseReference reference;
    String hotelName;
    DialogFragment dialogFragment;
    ArrayList<Orders> items = new ArrayList<>();
    ChildEventListener childEventListener;
    FirebaseAuth auth;
    FirebaseAuth.AuthStateListener stateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        setSupportActionBar((Toolbar) findViewById(R.id.orderToolbar));
        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("Orders");

        recyclerView = ((RecyclerView) findViewById(R.id.orderRecyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(OrderList.this, items);
        recyclerView.setAdapter(recyclerViewAdapter);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("/" + hotelName + "/orders");
        dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "loading");


        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ArrayList<OrderedItem> oder = new ArrayList<>();
                GenericTypeIndicator<ArrayList<OrderedItem>> t = new GenericTypeIndicator<ArrayList<OrderedItem>>() {
                };
                oder.addAll(dataSnapshot.getValue(t));
                dataSnapshot.getRef().removeValue();
                String key = dataSnapshot.getKey();
                Orders orders = new Orders(key, oder);
                items.add(orders);
                recyclerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

        reference.addChildEventListener(childEventListener);


        auth = FirebaseAuth.getInstance();
        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() == null) {
                    finish();
                    Toast.makeText(OrderList.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                }
            }
        };

        auth.addAuthStateListener(stateListener);
    }


    @Override
    protected void onResume() {
        super.onResume();
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ArrayList<OrderedItem> oder = new ArrayList<>();
                    GenericTypeIndicator<ArrayList<OrderedItem>> t = new GenericTypeIndicator<ArrayList<OrderedItem>>() {
                    };
                    oder.addAll(ds.getValue(t));
                    ds.getRef().removeValue();
                    String key = ds.getKey();
                    Orders orders = new Orders(key, oder);
                    items.add(orders);
                }
                recyclerViewAdapter.notifyDataSetChanged();
                dialogFragment.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.over_flow_menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Click confirm to logout");
        //dialog.setCancelable(true);
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
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        auth.removeAuthStateListener(stateListener);
        reference.removeEventListener(childEventListener);
    }
}

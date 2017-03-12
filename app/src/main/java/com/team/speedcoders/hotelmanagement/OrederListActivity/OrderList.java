package com.team.speedcoders.hotelmanagement.OrederListActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.team.speedcoders.hotelmanagement.ItemList.ItemsLIst;
import com.team.speedcoders.hotelmanagement.LogInActivity.LoginActivity;
import com.team.speedcoders.hotelmanagement.R;

public class OrderList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.over_flow_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Click confirm to logout");
        dialog.setCancelable(false);
        dialog.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getSharedPreferences(LoginActivity.MySharedPreference, Context.MODE_PRIVATE).edit().clear().apply();
                Toast.makeText(OrderList.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
                finish();
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
}

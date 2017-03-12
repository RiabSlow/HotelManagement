package com.team.speedcoders.hotelmanagement.RegisterActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.team.speedcoders.hotelmanagement.ItemList.ItemsLIst;
import com.team.speedcoders.hotelmanagement.LogInActivity.LoginActivity;
import com.team.speedcoders.hotelmanagement.OrederListActivity.OrderList;
import com.team.speedcoders.hotelmanagement.R;

public class RegisterActivity extends AppCompatActivity {

    final static String MySharedPreference = "mypreference";
    final static String HotelName = "hotelname";
    final static String UserName = "username";
    final static String Password = "password";
    final static String AsAuthor = "author";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}

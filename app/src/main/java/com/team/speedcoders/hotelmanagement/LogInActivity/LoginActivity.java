package com.team.speedcoders.hotelmanagement.LogInActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.team.speedcoders.hotelmanagement.ItemList.ItemsLIst;
import com.team.speedcoders.hotelmanagement.OrederListActivity.OrderList;
import com.team.speedcoders.hotelmanagement.R;
import com.team.speedcoders.hotelmanagement.RegisterActivity.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    public final static String MySharedPreference = "mypreference";
    public final static String HotelName = "hotelname";
    public final static String UserName = "username";
    public final static String Password = "password";
    public final static String AsAuthor = "author";
    SharedPreferences sharedPreferences;

    EditText hotelName, userName, password;
    Button login;
    CheckBox checkBox;
    TextView registerNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initiateAll();
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        sharedPreferences = getSharedPreferences(MySharedPreference, Context.MODE_PRIVATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sharedPreferences.contains(HotelName)) {
            if (sharedPreferences.contains(UserName)) {
                if (sharedPreferences.contains(Password)) {
                    nextActivity(sharedPreferences.getBoolean(AsAuthor, false));
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }


    private void initiateAll() {
        hotelName = (EditText) findViewById(R.id.hotelNameField);
        userName = (EditText) findViewById(R.id.userIdField);
        password = (EditText) findViewById(R.id.userPWField);
        checkBox = (CheckBox) findViewById(R.id.author);
        login = (Button) findViewById(R.id.login);
        registerNow = (TextView) findViewById(R.id.registerNow);

        View.OnClickListener clicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.login:
                        if (checkContrains())
                            nextActivity(checkBox.isChecked());
                        break;
                    case R.id.registerNow:
                        Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        login.setOnClickListener(clicked);
        registerNow.setOnClickListener(clicked);
    }

    private void nextActivity(boolean checked) {
        Intent intent;
        if (checked) {
            intent = new Intent(this, OrderList.class);
        } else {
            intent = new Intent(this, ItemsLIst.class);
        }
        startActivity(intent);
    }

    private boolean checkContrains() {
        String hotelName = this.hotelName.getText().toString();
        String userName = this.userName.getText().toString();
        String password = this.password.getText().toString();

        if (!hotelName.isEmpty()) {
            if (userName.length() >= 4) {
                if (password.length() >= 8) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(HotelName, hotelName);
                    editor.putString(UserName, userName);
                    editor.putString(Password, password);
                    editor.putBoolean(AsAuthor, checkBox.isChecked());
                    editor.apply();
                    return true;
                } else
                    Toast.makeText(this, "Password must have 8 character", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "User name must Contain atleast 4 character", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Hotel name must be filled", Toast.LENGTH_SHORT).show();
        return false;
    }
}

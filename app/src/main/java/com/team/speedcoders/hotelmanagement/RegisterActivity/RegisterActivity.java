package com.team.speedcoders.hotelmanagement.RegisterActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.team.speedcoders.hotelmanagement.ItemList.ItemsLIst;
import com.team.speedcoders.hotelmanagement.LogInActivity.LoginActivity;
import com.team.speedcoders.hotelmanagement.OrederListActivity.OrderList;
import com.team.speedcoders.hotelmanagement.R;

public class RegisterActivity extends AppCompatActivity {

    EditText hotelName, userId, password;
    String hotelNames, userNames, passwords;
    Button register;
    CheckBox checkBox;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initiateAll();

    }

    private void initiateAll() {
        hotelName = (EditText) findViewById(R.id.hotelNameField);
        userId = (EditText) findViewById(R.id.userIdField);
        password = (EditText) findViewById(R.id.userPWField);
        checkBox = (CheckBox) findViewById(R.id.author);
        register = (Button) findViewById(R.id.register);
        signIn = (TextView) findViewById(R.id.signIn);

        hotelNames = this.hotelName.getText().toString();
        userNames = this.userId.getText().toString();
        passwords = this.password.getText().toString();

        firebaseAuth=FirebaseAuth.getInstance();

        View.OnClickListener clicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotelNames =hotelName.getText().toString();
                userNames = userId.getText().toString();
                passwords = password.getText().toString();
                switch (v.getId()) {
                    case R.id.register:
                        firebaseAuth.createUserWithEmailAndPassword(userNames,passwords).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RegisterActivity.this,"Successfully registered",Toast.LENGTH_SHORT).show();
                                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                                    startActivity(intent);
                                }else
                                    Toast.makeText(RegisterActivity.this,"Unsuccessful register",Toast.LENGTH_SHORT).show();
                            }
                        });
                        break;
                    case R.id.signIn:
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
        register.setOnClickListener(clicked);
        signIn.setOnClickListener(clicked);

    }

    private boolean checkConstrains() {

        if (!hotelNames.isEmpty()) {
            if (userNames.length() >= 4) {
                if (passwords.length() >= 8) {
                    return true;
                } else
                    Toast.makeText(this, "Password must have 8 character", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "User name must Contain atleast 4 character", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Hotel name must be filled", Toast.LENGTH_SHORT).show();
        return false;
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
}

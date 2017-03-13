package com.team.speedcoders.hotelmanagement.LogInActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.team.speedcoders.hotelmanagement.OrederListActivity.OrderList;
import com.team.speedcoders.hotelmanagement.R;
import com.team.speedcoders.hotelmanagement.RegisterActivity.RegisterActivity;


public class LoginActivity extends AppCompatActivity {

    EditText userName, password;
    String userNames, passwords;
    Button login;
    CheckBox checkBox;
    TextView registerNow;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initiateAll();
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Loading", "");

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    progressDialog.dismiss();
                    nextActivity(checkBox.isChecked());
                } else progressDialog.dismiss();
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (authStateListener != null)
            firebaseAuth.removeAuthStateListener(authStateListener);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finish();
    }


    private void initiateAll() {
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
                        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Loading please wait", "");
                        if (checkConstrains()) {
                            progressDialog.setCancelable(false);
                            progressDialog.show();
                            firebaseAuth.signInWithEmailAndPassword(userNames, passwords).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        progressDialog.dismiss();
                                        nextActivity(checkBox.isChecked());
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(LoginActivity.this, "Error loging in", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else progressDialog.dismiss();
                        break;
                    case R.id.registerNow:
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
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

    @Override
    protected void onStop() {
        super.onStop();
    }

    private boolean checkConstrains() {
        userNames = this.userName.getText().toString();
        passwords = this.password.getText().toString();

        if (userNames.length() >= 4) {
            if (passwords.length() >= 8) {
                return true;
            } else
                Toast.makeText(this, "Password must have 8 character", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "User name must Contain atleast 4 character", Toast.LENGTH_SHORT).show();
        return false;
    }
}

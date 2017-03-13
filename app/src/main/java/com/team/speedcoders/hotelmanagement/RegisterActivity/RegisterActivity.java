package com.team.speedcoders.hotelmanagement.RegisterActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.UserProfileChangeRequest;
import com.team.speedcoders.hotelmanagement.R;

public class RegisterActivity extends AppCompatActivity {

    EditText userId, password, userName, confirmPW;
    String userIds, passwords, userNames, confirmPWs;
    Button register;
    CheckBox checkBox;
    TextView signIn;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener stateListener;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initiateAll();

    }

    private void initiateAll() {
        userId = (EditText) findViewById(R.id.userIdField);
        password = (EditText) findViewById(R.id.userPWField);
        confirmPW = (EditText) findViewById(R.id.userConfertPWField);
        userName = (EditText) findViewById(R.id.UserNameField);
        checkBox = (CheckBox) findViewById(R.id.author);
        register = (Button) findViewById(R.id.register);
        signIn = (TextView) findViewById(R.id.signIn);

        stateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    if ((firebaseUser.getDisplayName()) == null) {
                        UserProfileChangeRequest upc = new UserProfileChangeRequest.Builder().
                                setDisplayName(userNames).build();
                        firebaseUser.updateProfile(upc);
                        firebaseAuth.signOut();
                        progressDialog.dismiss();
                        finish();
                    } else
                        progressDialog.dismiss();
                }
            }
        };

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.addAuthStateListener(stateListener);

        View.OnClickListener clicked = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIds = userId.getText().toString();
                passwords = password.getText().toString();
                confirmPWs = confirmPW.getText().toString();
                userNames = userName.getText().toString();
                switch (v.getId()) {
                    case R.id.register:
                        if (checkConstrains()) {
                            progressDialog = new ProgressDialog(RegisterActivity.this);
                            progressDialog.setTitle("Registering....");
                            progressDialog.show();
                            firebaseAuth.createUserWithEmailAndPassword(userIds, passwords).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                                    } else
                                        Toast.makeText(RegisterActivity.this, "Unsuccessful registetion", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        break;
                    case R.id.signIn:
                        finish();
                        break;
                }
            }
        };
        register.setOnClickListener(clicked);
        signIn.setOnClickListener(clicked);

    }

    private boolean checkConstrains() {
        if (userNames.length() >= 4) {
            if (passwords.length() >= 8) {
                if (passwords.equals(confirmPWs))
                    return true;
                else Toast.makeText(this, "Passwords didn't match", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Password must have 8 character", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "User name must Contain atleast 4 character", Toast.LENGTH_SHORT).show();
        return false;
    }
}

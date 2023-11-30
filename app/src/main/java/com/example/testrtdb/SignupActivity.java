package com.example.testrtdb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    EditText sName, sEmail, sUsername, sPassword;
    TextView loginRedirect;
    Button sButton;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        sName = findViewById(R.id.signup_name);
        sEmail = findViewById(R.id.signup_email);
        sUsername = findViewById(R.id.signup_username);
        sPassword = findViewById(R.id.signup_password);
        loginRedirect = findViewById(R.id.loginRedirectText);
        sButton = findViewById(R.id.signup_button);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String name = sName.getText().toString().trim();
                String email = sEmail.getText().toString().trim();
                String username = sUsername.getText().toString().trim();
                String password = sPassword.getText().toString().trim();

                HelperClass helperClass = new HelperClass(name, email, username, password);
                reference.child(username).setValue(helperClass);
                Toast.makeText(SignupActivity.this, "You have signup succesfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });

        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
            }
        });
    }
}
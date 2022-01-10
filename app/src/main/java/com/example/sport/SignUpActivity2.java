package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.security.keystore.UserNotAuthenticatedException;
import android.util.Patterns;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.sql.Connection;

public class SignUpActivity2 extends AppCompatActivity implements View.OnClickListener {
    private boolean hasprofil = true;
    private TextView title;
    private EditText password,email,username;
    private Button signup;
    ConnectionSql connectionSql= new ConnectionSql();
    private User user;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        title = findViewById(R.id.title);
        password = findViewById(R.id.password);
        email = findViewById(R.id.Email);
        username = findViewById(R.id.username);
        signup = findViewById(R.id.signupbtm);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signupbtm:
                register();
                break;
            default:
                break;

        }
    }
    public void register(){
        String usernameS = username.getText().toString();
        String passwordS = password.getText().toString();
        String emailS = email.getText().toString();
        if(usernameS.isEmpty()){
            username.setError("please enter full username");
            username.requestFocus();
            return;
        }
        if(passwordS.isEmpty()){
            password.setError("please enter full username");
            password.requestFocus();
            return;
        }
        if(passwordS.length() < 10){
            password.setError("ivalid password min 10 characters");
            password.requestFocus();
            return;
        }
        if(emailS.isEmpty()){
            email.setError("please enter full username");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            email.setError("please enter valid email");
            email.requestFocus();
            return;
        }
        user = new User(usernameS,emailS,passwordS);
        boolean check = connectionSql.registerUser(user);
        if(check == true){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "fall", Toast.LENGTH_SHORT).show();
        }
    }
}
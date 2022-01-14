package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity2 extends AppCompatActivity implements View.OnClickListener {
    private EditText password,email;
    private Button login,signUp;
    ConnectionSql connectionSql= new ConnectionSql();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in2);
        password = findViewById(R.id.password);
        email = findViewById(R.id.Email);
        login = findViewById(R.id.loginbtm);
        signUp = findViewById(R.id.signuplink);
        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbtm:
                logUser();
                break;
            case R.id.signuplink:
                startActivity(new Intent(this, SignUpActivity2.class));
                break;
            default:
                break;
        }
    }
    public void logUser(){
        String passwordS = password.getText().toString();
        String emailS = email.getText().toString();
        if(passwordS.isEmpty()){
            password.setError("please enter password");
            password.requestFocus();
            return;
        }
        if(emailS.isEmpty()){
            email.setError("please enter email address");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            email.setError("please enter valid email");
            email.requestFocus();
            return;
        }
        boolean check = connectionSql.logUser(emailS,passwordS);
        if(check == true){
            startActivity(new Intent(this, AppPageActivity2.class));
        }
        else{
            Toast.makeText(this, "fall", Toast.LENGTH_SHORT).show();
        }
    }
}
package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordForgotActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText email;
    private Button reset;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_forgot);
        email = findViewById(R.id.EmailInput);
        reset = findViewById(R.id.ResetButton);
        reset.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == reset.getId()){
            resetPassword();
        }
    }
    public void resetPassword(){
        String emailS = email.getText().toString();
        if(emailS.isEmpty()){
            Toast.makeText(this, "please enter your email address", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            email.requestFocus();
            return;
        }
        auth.sendPasswordResetEmail(emailS).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PasswordForgotActivity.this, "Check your indox", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(PasswordForgotActivity.this, "Something went wrong try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
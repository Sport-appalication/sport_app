package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SignUpActivity2 extends AppCompatActivity {
    private boolean hasprofil = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        if(!hasprofil){
            Intent intent = new Intent(this, ProfilCreationActivity2.class);
            startActivity(intent);
        }
    }
}
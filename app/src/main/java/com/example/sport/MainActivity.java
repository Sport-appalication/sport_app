package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button logIn;
    private Button signUp;
    private boolean islogin = false;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(FirebaseAuth.getInstance().getCurrentUser() !=null)
            islogin = true;
        else
            islogin = false;
        logIn = findViewById(R.id.loginLink);
        signUp = findViewById(R.id.signuplink);
        logIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
        if(islogin){
            intent = new Intent(this, AppPageActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginLink:
                intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
                break;
            case R.id.signuplink:
                intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}

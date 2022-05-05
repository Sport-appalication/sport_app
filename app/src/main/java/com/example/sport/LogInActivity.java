package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.sport.database.DatabaseConnection;
import com.example.sport.database.DatabaseControl;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText password,email;
    private Button login,signUp,passwordReset;
    DatabaseControl databaseControl = new DatabaseControl();
    private ProgressBar progressBar;
    private RelativeLayout loginForm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        password = findViewById(R.id.password);
        email = findViewById(R.id.Email);
        login = findViewById(R.id.loginbtm);
        signUp = findViewById(R.id.signuplink);
        passwordReset = findViewById(R.id.ResetPassword);
        progressBar = findViewById(R.id.progress_circular_log);
        loginForm = findViewById(R.id.loginform);
        signUp.setOnClickListener(this);
        login.setOnClickListener(this);
        passwordReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginbtm:
                progressBar.setVisibility(View.VISIBLE);
                loginForm.setVisibility(View.INVISIBLE);
                logUser();
                progressBar.setVisibility(View.INVISIBLE);
                loginForm.setVisibility(View.VISIBLE);
                break;
            case R.id.signuplink:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.ResetPassword:
                startActivity(new Intent(this, PasswordForgotActivity.class));
                break;
            default:
                break;
        }
    }

    public void logUser(){
        String passwordS = password.getText().toString();
        String emailS = email.getText().toString();
        if(passwordS.isEmpty()){
            password.setError("Please enter password");
            password.requestFocus();
            return;
        }
        if(emailS.isEmpty()){
            email.setError("Please enter email address");
            email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            email.setError("Please enter valid email");
            email.requestFocus();
            return;
        }
        Log check = databaseControl.logUser(emailS,passwordS);
        System.out.println(check.getLog());
        if(check.getLog() == Log.Logs.EmailVerification){
            Toast.makeText(this, "Please check your indox to verify email", Toast.LENGTH_SHORT).show();
        }
        else if(check.getLog() == Log.Logs.LoginFailed){
            Toast.makeText(this, "Please check your details and try again", Toast.LENGTH_SHORT).show();
        }
        else if(check.getLog() == Log.Logs.LoginSuccess){
            startActivity(new Intent(this, AppPageActivity.class));
        }


    }
}
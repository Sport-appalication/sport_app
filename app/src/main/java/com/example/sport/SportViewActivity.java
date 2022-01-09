package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.nfc.TagLostException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Locale;

public class SportViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView sportname,numberofseion,numberofstep;
    private static Sport sport;
    private ImageView exercise,food,profil;
    Intent intent;
    private long startTime = 300000;
    private CountDownTimer countDownTimer;
    private boolean timerRunning = false;
    private long timeLeft = startTime;
    private Button start;
    private Button stop;
    private Button finish;
    private TextView timer;
    private RelativeLayout detail;
    private RelativeLayout started;
    public static void setSportView(Sport sport) {
        SportViewActivity.sport = sport;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_view);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        exercise.setImageResource(R.drawable.exercise_selected_icon);
        sport.setNumberofsesion(10);
        sport.setNumberofstep(10);
        sportname = findViewById(R.id.title);
        numberofseion = findViewById(R.id.numberofsesion);
        numberofstep = findViewById(R.id.numberofstep);
        sportname.setText(sport.getName());
        numberofstep.setText(""+sport.getNumberofstep());
        numberofseion.setText(""+sport.getNumberofsesion());
        start = findViewById(R.id.startbtm);
        timer = findViewById(R.id.timertext);
        detail = findViewById(R.id.sportdetails);
        started = findViewById(R.id.sportstared);
        finish = findViewById(R.id.finishsesion);
        stop = findViewById(R.id.stop);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startTime();
                detail.setVisibility(View.INVISIBLE);
                started.setVisibility(View.VISIBLE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startTime();
                countDownTimer.cancel();
                detail.setVisibility(View.VISIBLE);
                started.setVisibility(View.INVISIBLE);
                timer.setText("");
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish.setVisibility(View.INVISIBLE);
                startTime();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exercise_link:
                intent = new Intent(this, AppPageActivity2.class);
                startActivity(intent);
                break;
            case R.id.food_link:
                intent = new Intent(this, NutritionActivity.class);
                startActivity(intent);
                break;
            case R.id.profil_link:
                intent = new Intent(this, UserProfilActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
    public void startTime() {
        countDownTimer = new CountDownTimer(startTime,1000) {
            @Override
            public void onTick(long l) {
                timeLeft = l;
                updateText();
            }

            @Override
            public void onFinish() {
                finish.setVisibility(View.VISIBLE);
            }
        }.start();
    }
    public void updateText(){
        int minute = (int) (timeLeft /1000) / 60;
        int seconds = (int) (timeLeft /1000) % 60;
        String timertext = String.format(Locale.getDefault(),"%02d : %02d",minute,seconds);
        timertext = "cool down \n" +"    "+ timertext;
        timer.setText(timertext);
    }
}
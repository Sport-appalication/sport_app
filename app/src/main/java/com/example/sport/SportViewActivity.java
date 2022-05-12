package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sport.exercise.Sport;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class SportViewActivity extends AppCompatActivity {
    private TextView sportname,numberofseion,numberofstep, timer,sesionView;
    private static Sport sport;
    private ImageView iconV;
    Intent intent;
    private long startTime = 3000;
    private CountDownTimer countDownTimer;
    private long timeLeft = startTime;
    private Button start,stop, finish;
    private RelativeLayout detail,started;
    int cunumofsesion = 1;
    private MediaPlayer playSound;
    public static void setSportView(Sport sport) {
        SportViewActivity.sport = sport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_view);
        iconV = findViewById(R.id.exerciseImage);
        sport.setNumberofsesion(sport.getNumberofsesion());
        sport.setNumberofstep(sport.getNumberofstep());
        sportname = findViewById(R.id.title);
        numberofseion = findViewById(R.id.textsesion);
        numberofstep = findViewById(R.id.textStep);
        sportname.setText(sport.getName());
        sesionView = findViewById(R.id.sesioncount);
        numberofstep.setText("Number of reps: "+sport.getNumberofstep());
        numberofseion.setText("Number of session: "+sport.getNumberofsesion());
        start = findViewById(R.id.startbtm);
        timer = findViewById(R.id.timertext);
        detail = findViewById(R.id.sportdetails);
        started = findViewById(R.id.sportstared);
        finish = findViewById(R.id.finishsesion);
        stop = findViewById(R.id.stop);
        playSound = MediaPlayer.create(this,R.raw.soundeffect);
        Glide.with(this)
                .asGif()
                .load(sport.getImageUrl())
                .into(iconV);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.setText(sport.getNumberofstep() + " reps Go");
                detail.setVisibility(View.INVISIBLE);
                started.setVisibility(View.VISIBLE);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restart();
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish.setVisibility(View.INVISIBLE);
                startTime();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_sport);
        bottomNavigationView.setSelectedItemId(R.id.appPageActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        startActivity(new Intent(SportViewActivity.this, AppPageActivity.class));
                        break;
                    case R.id.nutritionActivity:
                        startActivity(new Intent(SportViewActivity.this, NutritionActivity.class));
                        break;
                    case R.id.userProfilActivity:
                        startActivity(new Intent(SportViewActivity.this, UserProfilActivity.class));
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
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
                playSound.start();
                cunumofsesion++;
                if(cunumofsesion < sport.getNumberofsesion()) {
                    timer.setText(sport.getNumberofstep() + " reps Go" + "\n" + (sport.getNumberofsesion() - cunumofsesion +1)+ " More session \n" +
                            "Go Again");
                    updatesesion();
                    finish.setVisibility(View.VISIBLE);
                }
                else{
                    updatesesion();
                    timer.setText("Last One");
                    stop.setText("Done");
                }
            }
        }.start();
    }

    public void updateText(){
        int minute = (int) (timeLeft /1000) / 60;
        int seconds = (int) (timeLeft /1000) % 60;
        String timertext = String.format(Locale.getDefault(),"%02d : %02d",minute,seconds);
        timertext = "Cool Down Time\n" + timertext;
        timer.setText(timertext);
    }

    public void  restart(){
        detail.setVisibility(View.VISIBLE);
        started.setVisibility(View.INVISIBLE);
        timer.setText("");
        finish.setVisibility(View.VISIBLE);
        sesionView.setText("Session: 1");
        stop.setText("Stop");
        cunumofsesion = 1;
    }

    public void updatesesion(){
        sesionView.setText("Session: " + cunumofsesion);
    }
}
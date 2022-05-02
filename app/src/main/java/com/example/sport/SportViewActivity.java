package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sport.exercise.Sport;

import java.util.Locale;

public class SportViewActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView sportname,numberofseion,numberofstep, timer,sesionView;
    private static Sport sport;
    private ImageView exercise,food,profil,iconV;
    Intent intent;
    private long startTime = 3000;
    private CountDownTimer countDownTimer;
    private long timeLeft = startTime;
    private Button start,stop, finish;
    private RelativeLayout detail,started;
    int cunumofsesion = 1;
    public static void setSportView(Sport sport) {
        SportViewActivity.sport = sport;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_view);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        iconV = findViewById(R.id.exerciseImage);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        exercise.setImageResource(R.drawable.exercise_selected_icon);
        sport.setNumberofsesion(5);
        sport.setNumberofstep(5);
        sportname = findViewById(R.id.title);
        numberofseion = findViewById(R.id.textsesion);
        numberofstep = findViewById(R.id.textStep);
        sportname.setText(sport.getName());
        sesionView = findViewById(R.id.sesioncount);
        numberofstep.setText("Number of reaps: "+sport.getNumberofstep());
        numberofseion.setText("number of session: "+sport.getNumberofsesion());
        start = findViewById(R.id.startbtm);
        timer = findViewById(R.id.timertext);
        detail = findViewById(R.id.sportdetails);
        started = findViewById(R.id.sportstared);
        finish = findViewById(R.id.finishsesion);
        stop = findViewById(R.id.stop);
        Glide.with(this)
                .asGif()
                .load(sport.getImageUrl())
                .into(iconV);

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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exercise_link:
                intent = new Intent(this, AppPageActivity.class);
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
                cunumofsesion++;
                if(cunumofsesion < sport.getNumberofsesion()) {
                    timer.setText("" + (sport.getNumberofsesion() - cunumofsesion +1)+ " More \n" +
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
        countDownTimer.cancel();
        detail.setVisibility(View.VISIBLE);
        started.setVisibility(View.INVISIBLE);
        timer.setText("");
        finish.setVisibility(View.VISIBLE);
        sesionView.setText("Sesion: 1");
        stop.setText("Stop");
        cunumofsesion = 1;
    }

    public void updatesesion(){
        sesionView.setText("Sesion: " + cunumofsesion);
    }
}
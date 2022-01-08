package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.TagLostException;
import android.os.Bundle;
import android.widget.TextView;

public class SportViewActivity extends AppCompatActivity {
    private TextView sportname,numberofseion,numberofstep;
    private static Sport sport;

    public static void setSportView(Sport sport) {
        SportViewActivity.sport = sport;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport_view);
        sport.setNumberofsesion(10);
        sport.setNumberofstep(10);
        sportname = findViewById(R.id.title);
        numberofseion = findViewById(R.id.numberofsesion);
        numberofstep = findViewById(R.id.numberofstep);
        sportname.setText(sport.getName());
        numberofstep.setText(""+sport.getNumberofstep());
        numberofseion.setText(""+sport.getNumberofsesion());
    }
}
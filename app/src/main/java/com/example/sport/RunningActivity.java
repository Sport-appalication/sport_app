package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class RunningActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etSource,etDestination;
    private Button btTrack;
    private ImageView exercise,food,profil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running);
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        exercise.setImageResource(R.drawable.exercise_selected_icon);
        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //When both value blank
                    Toast.makeText(getApplicationContext()
                            ,"Enter both location", Toast.LENGTH_SHORT).show();

                }else {
                    //When both value fill
                    //Display Track
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed, then redirect it to play store
        try{
            //When google map is installed
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Package
            intent.setPackage("com.google.android.apps.maps");
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //When google map is not installed
            //Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Intitialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exercise_link:
                startActivity(new Intent(this, AppPageActivity.class));
                break;
            case R.id.food_link:
                startActivity(new Intent(this, NutritionActivity.class));
                break;
            case R.id.profil_link:
                startActivity(new Intent(this, UserProfilActivity.class));
                break;
            default:
                break;
        }
    }
}
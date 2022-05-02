package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.sport.adapter.Sport_item_adapter;
import com.example.sport.exercise.Sport;

import java.util.ArrayList;

public class AppPageActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rviewSport;
    private ImageView exercise,food,profil;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page);
        rviewSport = findViewById(R.id.sportcardv);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        exercise.setImageResource(R.drawable.exercise_selected_icon);
        ArrayList<Sport> sports = new ArrayList<>();
        sports.add(new Sport("Running","Runnig outside using build in map",R.mipmap.finalrun));
        sports.add(new Sport("Wide Grip Push Ups","a conditioning exercise performed in a prone position by raising and lowering the body with the straightening and bending of the arms","https://flabfix.com/wp-content/uploads/2019/08/Wide-Grip-Push-Ups.gif",R.mipmap.pushuprun));
        sports.add(new Sport("Squat","a strength exercise in which the trainee lowers their hips from a standing position and then stands back up","https://flabfix.com/wp-content/uploads/2019/08/Air-Squat.gif",R.mipmap.squateifinal));
        sports.add(new Sport("Crunches","classic core exercise. It specifically trains your abdominal muscles","https://flabfix.com/wp-content/uploads/2019/05/Crunches.gif",R.mipmap.crusfinal));
        sports.add(new Sport("Dips","A dip is an upper-body strength exercise","https://flabfix.com/wp-content/uploads/2019/05/Dips.gif",R.mipmap.dipsfinal));
        sports.add(new Sport("Step Up Lunge","A step-up is a simple body resistance exercise that works muscles in the legs and buttocks","https://flabfix.com/wp-content/uploads/2019/06/Step-Up-Lunge.gif",R.mipmap.stepuplungefinal));
        sports.add(new Sport("Skipping Rope","","",R.mipmap.skippingropefinal));
        Sport_item_adapter adapter = new Sport_item_adapter(this);
        adapter.setSports(sports);
        rviewSport.setAdapter(adapter);
        rviewSport.setLayoutManager(new LinearLayoutManager(this));
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
}
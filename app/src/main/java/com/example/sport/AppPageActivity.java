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
        ArrayList<Sport> beginner = new ArrayList<>();
        beginner.add(new Sport("Running", R.mipmap.finalrun));
        beginner.add(new Sport("Wide Grip Push Ups", "https://flabfix.com/wp-content/uploads/2019/08/Wide-Grip-Push-Ups.gif", 5, 5,R.mipmap.pushuprun));
        beginner.add(new Sport("Squat", "https://flabfix.com/wp-content/uploads/2019/08/Air-Squat.gif", 5, 5,R.mipmap.squateifinal));
        beginner.add(new Sport("Crunches", "https://flabfix.com/wp-content/uploads/2019/05/Crunches.gif", 5, 5, R.mipmap.crusfinal));
        beginner.add(new Sport("Dips", "https://flabfix.com/wp-content/uploads/2019/05/Dips.gif", 5, 5, R.mipmap.dipsfinal));
        beginner.add(new Sport("Step Up Lunge", "https://flabfix.com/wp-content/uploads/2019/06/Step-Up-Lunge.gif",5, 5, R.mipmap.stepuplungefinal));
        beginner.add(new Sport("Skipping Rope", "", 5, 5, R.mipmap.skippingropefinal));
        ArrayList<Sport> intermediate = new ArrayList<>();
        intermediate.add(new Sport("Running", R.mipmap.finalrun));
        intermediate.add(new Sport("Wide Grip Push Ups", "https://flabfix.com/wp-content/uploads/2019/08/Wide-Grip-Push-Ups.gif", 10, 10,R.mipmap.pushuprun));
        intermediate.add(new Sport("Squat", "https://flabfix.com/wp-content/uploads/2019/08/Air-Squat.gif", 10, 10,R.mipmap.squateifinal));
        intermediate.add(new Sport("Crunches", "https://flabfix.com/wp-content/uploads/2019/05/Crunches.gif", 10, 10, R.mipmap.crusfinal));
        intermediate.add(new Sport("Dips", "https://flabfix.com/wp-content/uploads/2019/05/Dips.gif", 10, 10, R.mipmap.dipsfinal));
        intermediate.add(new Sport("Step Up Lunge", "https://flabfix.com/wp-content/uploads/2019/06/Step-Up-Lunge.gif",10, 10, R.mipmap.stepuplungefinal));
        intermediate.add(new Sport("Skipping Rope", "", 10, 10, R.mipmap.skippingropefinal));
        ArrayList<Sport> advanced = new ArrayList<>();
        advanced.add(new Sport("Running", R.mipmap.finalrun));
        advanced.add(new Sport("Wide Grip Push Ups", "https://flabfix.com/wp-content/uploads/2019/08/Wide-Grip-Push-Ups.gif", 15, 15,R.mipmap.pushuprun));
        advanced.add(new Sport("Squat", "https://flabfix.com/wp-content/uploads/2019/08/Air-Squat.gif", 15, 15,R.mipmap.squateifinal));
        advanced.add(new Sport("Crunches", "https://flabfix.com/wp-content/uploads/2019/05/Crunches.gif", 15, 15, R.mipmap.crusfinal));
        advanced.add(new Sport("Dips", "https://flabfix.com/wp-content/uploads/2019/05/Dips.gif", 15, 15, R.mipmap.dipsfinal));
        advanced.add(new Sport("Step Up Lunge", "https://flabfix.com/wp-content/uploads/2019/06/Step-Up-Lunge.gif",15, 15, R.mipmap.stepuplungefinal));
        advanced.add(new Sport("Skipping Rope", "", 5, 5, R.mipmap.skippingropefinal));
        Sport_item_adapter adapter = new Sport_item_adapter(this);

        adapter.setSports(advanced);
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
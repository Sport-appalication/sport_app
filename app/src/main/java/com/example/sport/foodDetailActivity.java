package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sport.food.Food;

public class foodDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Foodname,Fat,Calorie;
    private ImageView exercise, nutrution,profil;
    private static Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        exercise = findViewById(R.id.exercise_link);
        nutrution = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        nutrution.setImageResource(R.drawable.food_selected_icon);
        exercise.setOnClickListener(this);
        nutrution.setOnClickListener(this);
        profil.setOnClickListener(this);
        Foodname = findViewById(R.id.foodname);
        Fat = findViewById(R.id.Fat);
        Calorie = findViewById(R.id.callorie);
        Foodname.setText(food.getName());
        Calorie.setText("" + food.getCalory());
        Fat.setText(""+food.getFat());
    }

    public static void setFood(Food food) {
        foodDetailActivity.food = food;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exercise_link:
                startActivity(new Intent(this, AppPageActivity.class));
                break;
            case R.id.food_link:
                startActivity( new Intent(this, NutritionActivity.class));
                break;
            case R.id.profil_link:
                startActivity(new Intent(this, UserProfilActivity.class));
                break;
            default:
                break;
        }
    }
}
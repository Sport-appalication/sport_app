package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sport.food.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class foodDetailActivity extends AppCompatActivity  {
    private TextView Foodname,Fat,Calorie;
    private static Food food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);
        Foodname = findViewById(R.id.foodname);
        Fat = findViewById(R.id.Fat);
        Calorie = findViewById(R.id.callorie);
        Foodname.setText(food.getName());
        Calorie.setText("" + food.getCalory());
        Fat.setText(""+food.getFat());

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_food);
        bottomNavigationView.setSelectedItemId(R.id.nutritionActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        startActivity(new Intent(foodDetailActivity.this, AppPageActivity.class));
                        break;
                    case R.id.nutritionActivity:
                        startActivity(new Intent(foodDetailActivity.this, NutritionActivity.class));
                        break;
                    case R.id.userProfilActivity:
                        startActivity(new Intent(foodDetailActivity.this, UserProfilActivity.class));
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    public static void setFood(Food food) {
        foodDetailActivity.food = food;
    }

}
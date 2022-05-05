package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sport.adapter.FoodViewAdapter;
import com.example.sport.food.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NutritionActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText searchBar;
    private TextView textEroor;
    private RecyclerView foodsView;
    private Button seachbtm;
    private FoodViewAdapter adapter;
    ArrayList<Food> foodList = new ArrayList<Food>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition);
        textEroor = findViewById(R.id.errorDisplay);
        searchBar = findViewById(R.id.search_bar);
        foodsView = findViewById(R.id.rFoodV);
        seachbtm = findViewById(R.id.searchBtm);
        seachbtm.setOnClickListener(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_nutrution);
        bottomNavigationView.setSelectedItemId(R.id.nutritionActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        startActivity(new Intent(NutritionActivity.this, AppPageActivity.class));
                        break;
                    case R.id.nutritionActivity:
                        break;
                    case R.id.userProfilActivity:
                        startActivity(new Intent(NutritionActivity.this, UserProfilActivity.class));
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }
    public void setFood(String Respose){
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(Respose);
            JSONArray jsonFood = (JSONArray) json.get("hits");
            JSONObject jsonobj;
            JSONObject item;
            for(int i =0;i<jsonFood.size();i++) {
                jsonobj = (JSONObject) jsonFood.get(i);
                item = (JSONObject) jsonobj.get("fields");
                foodList.add(new Food(item.get("item_name").toString(),Double.parseDouble(item.get("nf_calories").toString()), Double.parseDouble(item.get("nf_total_fat").toString())));
            }
            if(foodList.isEmpty()) {
                textEroor.setText("no result");
            }else{
                adapter = new FoodViewAdapter(this);
                adapter.setFoodList(foodList);
                foodsView.setAdapter(adapter);
                foodsView.setLayoutManager(new LinearLayoutManager(this));
            }
        }catch (Exception e){
            e.printStackTrace();
            textEroor.setText("no result");
        }
    }
    public void getFoodApi(String food){
        OkHttpClient client = new OkHttpClient();
        String key = "55cc6bce48e5eadf670690fb0b5f454a";
        String f_url = "https://api.nutritionix.com/v1_1/search/" + food + "?results=0%3A20&cal_min=0&cal_max=50000&fields=item_name%2Cbrand_name%2Citem_id%2Cbrand_id%2Citem_description%2Cnf_protein%2Cnf_calories%2Cnf_total_carbohydrate%2Cnf_total_fat&appId=" +key;
        Request request = new Request.Builder()
                .url(f_url)
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String MyRespose = response.body().string();
                    NutritionActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            foodList = new ArrayList<Food>();
                            setFood(MyRespose);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchBtm:
                String food = searchBar.getText().toString();
                if(food.isEmpty()){
                    searchBar.requestFocus();
                    return;
                }
                getFoodApi(food);
                break;
            default:
                break;
        }
    }
}
package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.Toast;

import com.example.sport.adapter.Sport_item_adapter;
import com.example.sport.database.DatabaseConnection;
import com.example.sport.database.DatabaseControl;
import com.example.sport.exercise.Sport;
import com.example.sport.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AppPageActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rviewSport;
    private Intent intent;
    private ProgressBar progressBar;
    private ScrollView sports;
    private DatabaseConnection connection;
    private FirebaseUser user;
    private User userObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page);
        rviewSport = findViewById(R.id.sportcardv);
        progressBar= findViewById(R.id.progress_circular);
        sports = findViewById(R.id.sports);
        sports.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
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
        advanced.add(new Sport("Skipping Rope", "", 15, 15, R.mipmap.skippingropefinal));
        Sport_item_adapter adapter = new Sport_item_adapter(this);
        DatabaseControl control = new DatabaseControl();
        connection = new DatabaseConnection();
        user = FirebaseAuth.getInstance().getCurrentUser();
        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_app);
        bottomNavigationView.setSelectedItemId(R.id.appPageActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        break;
                    case R.id.nutritionActivity:
                        intent = new Intent(AppPageActivity.this, NutritionActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.userProfilActivity:
                        intent = new Intent(AppPageActivity.this, UserProfilActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
        if(user !=null) {
            connection.getReference().child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userObject = snapshot.getValue(User.class);
                    System.out.println(userObject.getLevel());
                    if(userObject !=null){
                        if(userObject.getLevel() == 1) {
                            adapter.setSports(beginner);
                        }
                        else if(userObject.getLevel() == 2){
                            adapter.setSports(intermediate);
                        }
                        else if(userObject.getLevel() == 3){
                            adapter.setSports(advanced);
                        }
                        rviewSport.setAdapter(adapter);
                        rviewSport.setLayoutManager(new LinearLayoutManager(AppPageActivity.this));
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(AppPageActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show();
        }
        sports.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View view) {

    }
}
package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport.database.DatabaseConnection;
import com.example.sport.database.DatabaseControl;
import com.example.sport.user.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LevelActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button advance,beginner,intermediate;
    private TextView currentLevl;
    private DatabaseControl databaseControl;
    private User userObject;
    private FirebaseUser user;
    private DatabaseConnection connection;
    private ImageView exercise,food,profil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);
        advance = findViewById(R.id.advance);
        intermediate = findViewById(R.id.intermediate);
        beginner = findViewById(R.id.beginner);
        databaseControl = new DatabaseControl();
        connection = new DatabaseConnection();
        user = FirebaseAuth.getInstance().getCurrentUser();
        beginner.setOnClickListener(this);
        intermediate.setOnClickListener(this);
        advance.setOnClickListener(this);
        currentLevl = findViewById(R.id.currentLevel);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        profil.setImageResource(R.drawable.profil_selected_icon);

        if(user !=null) {
            connection.getReference().child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userObject = snapshot.getValue(User.class);
                    currentLevl = findViewById(R.id.currentLevel);
                    System.out.println(userObject.getLevel());
                    if(userObject !=null){
                        if(userObject.getLevel() == 1) {
                            currentLevl.setText("Current level: Beginner");
                        }
                        else if(userObject.getLevel() == 2){
                            currentLevl.setText("Current level: Intermediate");
                        }
                        else if(userObject.getLevel() == 3){
                            currentLevl.setText("Current level: Advance");
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(LevelActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            Toast.makeText(this, "something wrong", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.advance:
                databaseControl.updateLevel(3);
                currentLevl.setText("Current level: Advance");
                break;
            case R.id.intermediate:
                databaseControl.updateLevel(2);
                currentLevl.setText("Current level: Intermediate");
                break;
            case R.id.beginner:
                databaseControl.updateLevel(1);
                currentLevl.setText("Current level: Beginner");
                break;
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
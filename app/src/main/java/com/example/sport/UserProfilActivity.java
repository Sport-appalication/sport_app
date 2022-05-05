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
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport.adapter.UserSettingAdapter;
import com.example.sport.database.DatabaseConnection;
import com.example.sport.user.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfilActivity extends AppCompatActivity{
    private RecyclerView rview;
    private TextView username;
    private FirebaseUser user;
    private DatabaseConnection reference;
    private String userId;
    private User userObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = findViewById(R.id.user_name);
        setContentView(R.layout.activity_user_profil);
        rview = findViewById(R.id.usersetting);
        UserSettingAdapter adapter = new UserSettingAdapter(this);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = new DatabaseConnection();
        userId = user.getUid();
        rview.setAdapter(adapter);
        rview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        reference.getReference().child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userObject = snapshot.getValue(User.class);
                if(userObject !=null){
                    username = findViewById(R.id.user_name);
                    String userusername = userObject.getUsername();
                    username.setText(userusername);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(UserProfilActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_profile);
        bottomNavigationView.setSelectedItemId(R.id.userProfilActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        startActivity(new Intent(UserProfilActivity.this, AppPageActivity.class));
                        break;
                    case R.id.nutritionActivity:
                        startActivity(new Intent(UserProfilActivity.this, NutritionActivity.class));
                        break;
                    case R.id.userProfilActivity:
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }
}
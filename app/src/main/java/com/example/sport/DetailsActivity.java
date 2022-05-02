package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.controls.Control;
import android.service.controls.actions.BooleanAction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport.database.DatabaseConnection;
import com.example.sport.database.DatabaseControl;
import com.example.sport.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView exercise,food,profil;
    private TextView username,email;
    private Button change;
    private EditText newuserN;
    private DatabaseControl databaseControl;
    private String usernameS;
    private DatabaseConnection connection;
    private FirebaseUser user;
    private String userId;
    private User userObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        profil.setImageResource(R.drawable.profil_selected_icon);
        change = findViewById(R.id.change);
        newuserN = findViewById(R.id.newusername);
        change.setOnClickListener(this);
        databaseControl = new DatabaseControl();
        connection = new DatabaseConnection();
        user = FirebaseAuth.getInstance().getCurrentUser();
        if(user !=null) {
            connection.getReference().child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userObject = snapshot.getValue(User.class);
                    if(userObject !=null){
                        username = findViewById(R.id.usernameplaceholder);
                        email = findViewById(R.id.EmailPLacehold);
                        username.setText(userObject.getUsername());
                        email.setText(userObject.getEmail());
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(DetailsActivity.this, "something wrong happened", Toast.LENGTH_SHORT).show();
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
            case R.id.exercise_link:
                startActivity(new Intent(this, AppPageActivity.class));
                break;
            case R.id.food_link:
                startActivity(new Intent(this, NutritionActivity.class));
                break;
            case R.id.profil_link:
                startActivity(new Intent(this, UserProfilActivity.class));
                break;
            case R.id.change:
                usernameS = newuserN.getText().toString();
                if(usernameS.isEmpty()){
                    newuserN.setError("please enter a username");
                    newuserN.requestFocus();
                    break;
                }
                boolean succuess = databaseControl.updateusenamre(usernameS);
                if(succuess){
                    Toast.makeText(this, "name updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, DetailsActivity.class));
                }
                else{
                    Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;
    private Button change;
    private User userObject;
    private EditText newuserN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = findViewById(R.id.usernameplaceholder);
        email = findViewById(R.id.EmailPLacehold);
        setContentView(R.layout.activity_details);
        exercise = findViewById(R.id.exercise_link);
        food = findViewById(R.id.food_link);
        profil = findViewById(R.id.profil_link);
        exercise.setOnClickListener(this);
        food.setOnClickListener(this);
        profil.setOnClickListener(this);
        profil.setImageResource(R.drawable.profil_selected_icon);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference(User.class.getSimpleName());
        userId = user.getUid();
        change = findViewById(R.id.change);
        newuserN = findViewById(R.id.newusername);
        change.setOnClickListener(this);
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userObject = snapshot.getValue(User.class);
                if(user !=null){
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.exercise_link:
                startActivity(new Intent(this, AppPageActivity2.class));
                break;
            case R.id.food_link:
                startActivity(new Intent(this, NutritionActivity.class));
                break;
            case R.id.profil_link:
                startActivity(new Intent(this, UserProfilActivity.class));
                break;
            case R.id.change:
                String usernameS = newuserN.getText().toString();
                if(usernameS.isEmpty()){
                    newuserN.setError("please enter full username");
                    newuserN.requestFocus();
                    break;
                }
                updateusenamre(usernameS);
                break;
            default:
                break;
        }
    }
    public void updateusenamre(String newUserName){
        HashMap newusername = new HashMap();
        newusername.put("username",newUserName);
        reference.child(userId).updateChildren(newusername).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    username.setText(newUserName);
                    Toast.makeText(DetailsActivity.this, "Username Changed", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(DetailsActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
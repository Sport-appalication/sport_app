package com.example.sport;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.StrictMode;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConnectionSql {
    DatabaseReference databaseReference;
    private boolean success = false;
    private FirebaseAuth mAuth;
    public ConnectionSql() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(User.class.getSimpleName());
        mAuth = FirebaseAuth.getInstance();
    }
    public boolean registerUser(User user){
        mAuth.createUserWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            databaseReference.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        ConnectionSql.this.success = true;
                                    }
                                    else {
                                        ConnectionSql.this.success = false;
                                    }
                                }
                            });
                        }
                        else{
                            ConnectionSql.this.success = true;
                        }
                    }
                });
        return success;
    }
    public boolean logUser(String email,String password){
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    ConnectionSql.this.success = true;
                }
                else {
                    ConnectionSql.this.success = false;
                }
            }
        });
        return success;
    }
    public void logout(){
        FirebaseAuth.getInstance().signOut();
    }
}

package com.example.sport.database;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.sport.Log;
import com.example.sport.LogInActivity;
import com.example.sport.user.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Currency;
import java.util.HashMap;

public class DatabaseControl {
    DatabaseConnection connection = new DatabaseConnection();
    Log log;
    boolean success;
    private User userObject;
    private FirebaseUser user;
    private User currentUser;
    public DatabaseControl(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        log = new Log();
        if(user != null) {
            connection.getDatabaseReference().child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    userObject = snapshot.getValue(User.class);
                    if (user != null) {
                        DatabaseControl.this.setCurrentUser(userObject);
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
    public Boolean registerUser(User user,String passWord){
        connection.getmAuth().createUserWithEmailAndPassword(user.getEmail(), passWord)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            connection.getDatabaseReference().child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        DatabaseControl.this.success = true;
                                    }
                                    else {
                                        DatabaseControl.this.success = false;
                                    }
                                }
                            });
                        }
                        else{
                            DatabaseControl.this.success = true;
                        }
                    }
                });
        return success;
    }
    public Log logUser(String email,String password){
        connection.getmAuth().signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()) {
                        DatabaseControl.this.log.setLog(Log.Logs.LoginSuccess);
                    }
                    else{
                        verifieEmail(user);
                        DatabaseControl.this.log.setLog(Log.Logs.EmailVerification);
                        logout();
                    }
                }
                else {
                    DatabaseControl.this.log.setLog(Log.Logs.LoginFailed);
                }
            }
        });
        return this.log;
    }
    public boolean updateusenamre(String newUserName){
        HashMap newusername = new HashMap();
        newusername.put("username",newUserName);
        connection.getDatabaseReference().child(user.getUid()).updateChildren(newusername).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    success = true;
                }
                else {
                    success = false;
                }
            }
        });
        return success;
    }
    public boolean updateLevel(int level){
        HashMap newusername = new HashMap();
        newusername.put("level",level);
        connection.getDatabaseReference().child(user.getUid()).updateChildren(newusername).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    success = true;
                }
                else {
                    success = false;
                }
            }
        });
        return success;
    }
    public void verifieEmail(FirebaseUser user){
        user.sendEmailVerification();
    }
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public void logout(){
        FirebaseAuth.getInstance().signOut();
    }
}

package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class AppPageActivity2 extends AppCompatActivity {
    private RecyclerView rviewSport;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_page2);
        rviewSport = findViewById(R.id.sportcardv);
        ArrayList<Sport> sports = new ArrayList<>();
        sports.add(new Sport("sport1","lala"));
        sports.add(new Sport("sport2","lala"));
        sports.add(new Sport("sport3","lala"));
        sports.add(new Sport("sport4","lala"));
        Sport_item_adapter adapter = new Sport_item_adapter(this);
        adapter.setSports(sports);
        rviewSport.setAdapter(adapter);
        rviewSport.setLayoutManager(new LinearLayoutManager(this));
    }
}
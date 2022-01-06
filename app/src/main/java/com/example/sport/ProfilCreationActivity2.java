package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ProfilCreationActivity2 extends AppCompatActivity {
    private RecyclerView rview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_creation2);
        rview = findViewById(R.id.rviewBodyType);
        ArrayList<BodyType> bodyTypes = new ArrayList<>();
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypes.add(new BodyType("emma","dwfwd"));
        bodyTypeList list = new bodyTypeList(this);
        list.setbodyTypes(bodyTypes);
        rview.setAdapter(list);
        rview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
    }
}
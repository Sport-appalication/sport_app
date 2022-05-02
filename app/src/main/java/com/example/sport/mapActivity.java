package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mapActivity2 extends AppCompatActivity {

    EditText etSource,etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //When both value blank
                    Toast.makeText(getApplicationContext()
                        ,"Enter both location", Toast.LENGTH_SHORT).show();

                }else {
                    //When both value fill
                    //Display Track
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed, then redirect it to play store
        try{
            //When google map is installed
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Package
            intent.setPackage("com.google.android.apps.maps");
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //When google map is not installed
            //Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Intitialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);

        }
    }
}
package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class mapActivity2 extends AppCompatActivity {

    EditText etSource,etDestination;
    Button btTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        btTrack = findViewById(R.id.bt_track);

        btTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sSource = etSource.getText().toString().trim();
                String sDestination = etDestination.getText().toString().trim();

                //Check condition
                if (sSource.equals("") && sDestination.equals("")){
                    //When both value blank
                    Toast.makeText(getApplicationContext()
                        ,"Enter both location", Toast.LENGTH_SHORT).show();

                }else {
                    //When both value fill
                    //Display Track
                    DisplayTrack(sSource,sDestination);
                }
            }
        });
    }

    private void DisplayTrack(String sSource, String sDestination) {
        //If the device does not have a map installed, then redirect it to play store
        try{
            //When google map is installed
            Uri uri = Uri.parse("https://www.google.co.in/maps/dir/" + sSource + "/" + sDestination);
            //Initialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Package
            intent.setPackage("com.google.android.apps.maps");
            //Set flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);
        }catch (ActivityNotFoundException e){
            //When google map is not installed
            //Initialize uri
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            //Intitialize intent with action view
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            //Set Flag
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //Start activity
            startActivity(intent);

        }
    }
}

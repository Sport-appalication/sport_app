package com.example.sport;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class mapActivity2 extends AppCompatActivity {

    EditText etSource,etDestination;
    TextView textView;
    String sType;
    double lat1 = 0, long1 = 0, lat2 = 0, long2 = 0;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);

        //Assign Variables
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        textView = findViewById(R.id.text_view);

        //Initialize places
        Places.initialize(getApplicationContext(), "AIzaSyCYd9DNtP8fAnic_H5XwgCef7dmqj_7vB0");

        //Set edit text non focusable
        etSource.setFocusable(false);
        etSource.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                sType = "source";

                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);
                //create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY,fields
                ).build(mapActivity2.this);
                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        //Set edit text non focusable
        etDestination.setFocusable(false);
        etDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Define type
                sType = "destination";

                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY,fields
                ).build(mapActivity2.this);

                //Start activity result
                startActivityForResult(intent,100);
            }
        });

        textView.setText("0.0 Kilometers");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Check condition
        if (requestCode == 100 && resultCode == RESULT_OK) {

            Place place = Autocomplete.getPlaceFromIntent(data);
            //Check condtion
            if (sType.equals("source")){
                //when type is source
                //Increase flag value
                flag++;
                //Set address on edit text
                etSource.setText(place.getAddress());
                //Get latitude and longitude
                String sSource = String.valueOf(place.getLatLng());
                sSource = sSource.replaceAll("lat/lng", "");
                sSource = sSource.replace("(", "");
                sSource = sSource.replace(")", "");
                String[] split = sSource.split(",");
                lat1 = Double.parseDouble(split[0]);
                long1 = Double.parseDouble(split[1]);
            }else {
                //When type is destination
                //Increase flag value
                flag++;
                //Set address on edit
                etDestination.setText(place.getAddress());
                //Get Latitude and Longitude
                String sDestination = String.valueOf(place.getLatLng());
                sDestination = sDestination.replaceAll("lat/lng", "");
                sDestination = sDestination.replace("(", "");
                sDestination = sDestination.replace(")", "");
                String[] split = sDestination.split(",");
                lat2 = Double.parseDouble(split[0]);
                long2 = Double.parseDouble(split[1]);
            }

            //check condition
            if (flag >= 2) {
                //When flag is greater than and equal to 2
                //Calculate distance
                distance(lat1,long1,lat2,long2);
            }
        }else if (requestCode == AutocompleteActivity.RESULT_ERROR){
            //When Error
            //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
            //Display toast
            Toast.makeText(getApplicationContext(),status.getStatusMessage()
                    ,Toast.LENGTH_SHORT).show();
        }
    }

    private void distance(double lat1, double long1, double lat2, double long2){
        //Calculate longitude difference
        double longDiff = long1 - long2;
        //Calculate distance
        double distance = Math.sin(deg2rad(lat1))
                * Math.sin(deg2rad(lat2))
                + Math.cos(deg2rad(lat1))
                * Math.cos(deg2rad(lat2))
                * Math.cos(deg2rad(longDiff));
        distance = Math.acos(distance);
        //Convert distance radian to degree
        distance = rad2deg(distance);
        //Distance in miles
        distance = distance * 60 * 1.1515;
        //Distance in kilometres
        distance = distance * 1.609344;
        //Set distance on text view
        textView.setText(String.format(Locale.US,"%2f Kilometers , distnace"));
    }

    //Convert radian to degree
    private double rad2deg (double distance) {
        return (distance * 180.0 / Math.PI);
    }

    //Convert degree to radian
    private double deg2rad(double lat1) {
        return (lat1*Math.PI/180.0);
    }



}

package com.example.sport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //Initialize varibale
    EditText etSource,etDestination;
    TextView textView;
    String sType;
    double lat1 = 0, long1 = 0, lat2 = 0, long2 = 0;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,ProfilCreationActivity2.class);
        startActivity(intent);

        //Assign variable
        etSource = findViewById(R.id.et_source);
        etDestination = findViewById(R.id.et_destination);
        textView = findViewById(R.id.text_view);

        //Initialize places
        Places.initialize(getApplicationContext(), s "AIzaSyCYd9DNtP8fAnic_H5XwgCef7dmqj_7vB0");

        //Set edit text non focusable
        etsource.setFocusable(false);
        etSource.setOnClickListener(new View.OnClickerListener(){
            @Override
            public void onClick(View v){
                sType = "source";

                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                    , Place.Field.LAT_LNG);
                //create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY,fields
                ).build(MainActivity.this);
                //Start activity result
                startActivityForResult(intent, 100);
            }
        });

        //Set edit text non focusable
        etDestination.setfocusable(false);
        etDestination.setOnClickListener(new View.OnClickListener() {
            @override
            public void onClick(View v) {

                //Define type
                sType = "destination";

                //Initialize place field list
                List<Place.Field> fields = Arrays.asList(Place.Field.ADDRESS
                        , Place.Field.LAT_LNG);
                //Create intent
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.OVERLAY,fields
                ).build(MainActivity.this);

                //Start activity result
                startActivityForResult(intent,100);
            }
        });

        //Set text on text view
        textView.setText("0.0 Kilometers");
    }
    @override
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
                String sSource = String.value0f(place.getLatLng());
                sSource = sSource.replaceAll("lat/lng", "");
                sSource = sSource.replace("(", "");
                sSource = sSource.replace(")", "");
                String[] split = source.split(",");
                lat1 = Double.parseDouble(split[0]);
                long1 = Double.parseDouble(split[1]);
            }else {
                //When type is destination
                //Increase flag value
                flag++;
                //Set address on edit
                etDestination.setText(place.getAddress());
                //Get Latitude and Longitude
                String sDestination = String.value0f(place.getLatLng());
                sDestination = sDestination.replaceAll("lat/lng", "");
                sDestination = sDestination.replace("(", "");
                sDestination = sDestination.replace(")", "");
                String[] split = sDestination.split(",");
                lat2 = Double.parseDouble(split[0]);
                long2 = Double.parseDouble(split[1]);
            }

        //check condition
            if (flag ›= 2) {
        //When flag is greater than and equal to 2
        //Calculate distance
                distance(lat1,long1,lat2,long2);
            }
        }else if (requestCode == AutocompleteActivity.RESULT_ERROR){
        //When Error
        //Initialize status
            Status status = Autocomplete.getStatusFromIntent(data);
        //Display toast
            Toast.makeText (getApplicationContext(),status.getStatusMessage() ,Toast.LENGTH_SHORT).show();
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
    textView.setText(String.formant(Locale.US,"%2f Kilometers , distnace"));
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

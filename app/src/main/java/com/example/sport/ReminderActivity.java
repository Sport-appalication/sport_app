package com.example.sport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sport.databinding.ActivityMainBinding;
import com.example.sport.reminder.reminderReciver;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.Calendar;

public class ReminderActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Button cancelReminder, setTime,picktime;
    private MaterialTimePicker timePicker;
    private TextView showtime;
    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        creatnotifchanel();
        cancelReminder = findViewById(R.id.cancelReminder);
        setTime = findViewById(R.id.setTime);
        picktime = findViewById(R.id.picktime);
        showtime = findViewById(R.id.time);
        picktime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimer();
            }
        });
        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlarm();
            }
        });
        cancelReminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelReminde();
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navBar_reminder);
        bottomNavigationView.setSelectedItemId(R.id.userProfilActivity);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.appPageActivity:
                        startActivity(new Intent(ReminderActivity.this, AppPageActivity.class));
                        break;
                    case R.id.nutritionActivity:
                        startActivity(new Intent(ReminderActivity.this, NutritionActivity.class));
                        break;
                    case R.id.userProfilActivity:
                        startActivity(new Intent(ReminderActivity.this, UserProfilActivity.class));
                        break;
                    default:
                        break;
                }

                return false;
            }
        });
    }

    private void cancelReminde() {
        Intent intent= new Intent(this, reminderReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        if(alarmManager ==null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Toast.makeText(this, "Remider Canceled", Toast.LENGTH_SHORT).show();
        }

    }

    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent= new Intent(this,reminderReciver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        alarmManager.setInexactRepeating(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this, "Reminder Set", Toast.LENGTH_SHORT).show();
    }

    private void showTimer() {
        timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(0)
                .build();
        timePicker.show(getSupportFragmentManager(),"androidChanel");
        timePicker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showtime.setText(timePicker.getHour() + " : "  + timePicker.getMinute());
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }

    private void creatnotifchanel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "foxandriodReminderChanenel";
            String description = "reminder work out";
            int Inportance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("androidChanel",name,Inportance);
            channel.setDescription(description);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }
}
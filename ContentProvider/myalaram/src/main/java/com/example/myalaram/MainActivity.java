package com.example.myalaram;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fab;
    TextView textView;
    Switch aSwitch;
    int set_hour;
    int set_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aSwitch = findViewById(R.id.mission1_switch);
        fab = findViewById(R.id.mission1_fab);
        textView = findViewById(R.id.mission1_time);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        set_hour = i;
                        set_minute = i1;
                    }
                };

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,listener,0,0,true);
                timePickerDialog.show();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,set_hour);
                calendar.set(Calendar.MINUTE,set_minute);
                SimpleDateFormat sd = new SimpleDateFormat("HH:mm");
                textView.setText(sd.format(calendar.getTime()));

                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent intent = new Intent(MainActivity.this,RingActivity.class);
                PendingIntent alarmIntent=PendingIntent.getActivity(this, 100, bIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                manager.set(AlarmManager.RTC,System.currentTimeMillis()+10000,pendingIntent);
            }
        });
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
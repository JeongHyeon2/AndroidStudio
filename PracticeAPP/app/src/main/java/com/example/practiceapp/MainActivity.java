package com.example.practiceapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Animatable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                Toast.makeText(MainActivity.this,dm.widthPixels+"dp/"+dm.heightPixels+"dp",Toast.LENGTH_SHORT).show();

            }
        });


    }
    float initX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            initX = event.getRawX();
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            float diffX = initX - event.getRawX();
            if(diffX>30) Toast.makeText(MainActivity.this,"왼쪽으로 화면 밀었음",Toast.LENGTH_SHORT).show();
            else if(diffX<-30)  Toast.makeText(MainActivity.this,"오른쪽으로 화면 밀었음",Toast.LENGTH_SHORT).show();
        }
        return super.onTouchEvent(event);
    }
    long initTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if(System.currentTimeMillis()-initTime>3000){
                Toast.makeText(MainActivity.this,"종료하려면 한번더 누르세요",Toast.LENGTH_SHORT).show();
                initTime = System.currentTimeMillis();
            }
            else{
                finish();
            }

        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
       // super.onBackPressed();
    }
}

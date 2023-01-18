package com.example.broadcastreceiverexample;

import androidx.appcompat.app.AppCompatActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if(action.equals(Intent.ACTION_POWER_CONNECTED)){
                    Toast.makeText(context,"충전중!",Toast.LENGTH_SHORT).show();
                }else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)){
                    Toast.makeText(context,"충전 해제!",Toast.LENGTH_SHORT).show();
                }
            }
        };
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_CONNECTED));
        registerReceiver(batteryReceiver,new IntentFilter(Intent.ACTION_POWER_DISCONNECTED));


        Intent intent = new Intent("com.example.ACTION_MY_RECEIVER");
        sendBroadcast(intent);







    }
}
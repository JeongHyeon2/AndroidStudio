package com.example.servicecomponent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this,PlayService.class);
        startService(intent);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                // 서비스가 구동하는 시점에 호출
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                // unbindService 함수로 인해 서비스가 종료된 시점에 호출
            }
        };
        Intent bIntent = new Intent(this,BindServie.class);
        bindService(bIntent,connection, Context.BIND_AUTO_CREATE);
        unbindService(connection);
    }


}
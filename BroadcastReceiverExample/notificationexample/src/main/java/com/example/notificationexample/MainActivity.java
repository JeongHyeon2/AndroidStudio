package com.example.notificationexample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder;



        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            String channel1Id = "one - channel";
            String channelName = "CH1";
            String channelDescription = "My Channel 1";
            NotificationChannel channel1 = new NotificationChannel(channel1Id,channelName,NotificationManager.IMPORTANCE_DEFAULT);
            channel1.setDescription(channelDescription);

            //각종 채널 설정
            channel1.enableLights(true);
            channel1.setLightColor(Color.RED);
            channel1.enableVibration(true);
            channel1.setVibrationPattern(new long[]{100,200,300});

            manager.createNotificationChannel(channel1);
            builder = new NotificationCompat.Builder(this,channel1Id);
            builder.setSmallIcon(android.R.drawable.ic_notification_overlay); // 작은 아이콘 이미지
            builder.setWhen(System.currentTimeMillis()); //시간
            builder.setContentTitle("Content Title"); // 확장 내용의 타이틀 문자열
            builder.setContentText("Content Massage");  // 확장 내용의 본문 문자열
            builder.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE); // 퍼미션 필요
            builder.setAutoCancel(true); // 터치 시 자동 삭제 여부

            Intent intent = new Intent(this,MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(this,100,intent, PendingIntent.FLAG_MUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);

            Bitmap largeIcon = BitmapFactory.decodeResource(getResources(),R.drawable.noti_big);
            builder.setLargeIcon(largeIcon);
            builder.addAction(new NotificationCompat.Action.Builder(android.R.drawable.ic_menu_share,"ACTION1",pendingIntent).build());

            builder.setFullScreenIntent(pendingIntent,true);

        }else{
            builder = new NotificationCompat.Builder(this);
        }

       // manager.notify(21,builder.build());
    }
}
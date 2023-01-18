package com.example.part7_20;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class PlayService extends Service implements MediaPlayer.OnCompletionListener {
    MediaPlayer player;
    String filepath;
    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String mode = intent.getStringExtra("mode");
            if(mode!=null){
                if(mode.equals("start")){
                    if(player!=null&&player.isPlaying()){
                        try {
                            player.stop();
                            player.release();
                            player = null;
                            player = new MediaPlayer();
                            player.setDataSource(filepath);
                            player.prepare();
                            player.start();

                            Intent aIntent = new Intent("com.example.PLAY_TO_ACTIVITY");
                            aIntent.putExtra("mode","start");
                            aIntent.putExtra("duration",player.getDuration());
                            sendBroadcast(aIntent);
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }else if(mode.equals("stop")){
                        if(player!=null&&player.isPlaying()){
                            player.stop();
                            player.release();
                            player = null;
                        }
                    }
                }
            }
        }
    };

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        //음악이 끝나면
        Intent intent = new Intent("com.example.PLAY_TO_ACTIVITY");
        intent.putExtra("mode","stop");
        sendBroadcast(intent);
        stopSelf();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(receiver,new IntentFilter("com.example.PLAY_TO_SERVICE"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        filepath  = intent.getStringExtra("filePath");
        // 이미 Service가 이전에 구동되어 음악이 플레이 되는 상황
        if (player != null) {
            Intent aIntent = new Intent("com.example.PLAY_TO_ACTIVITY");
            aIntent.putExtra("mode","restart");
            aIntent.putExtra("duration",player.getDuration());
            aIntent.putExtra("current",player.getCurrentPosition());
            sendBroadcast(aIntent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet");
    }
}

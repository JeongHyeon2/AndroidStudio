package com.example.andoridstudy_14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView startView;
    ImageView pauseView;
    TextView textView;
    OneThread oneThread;
    MyAsyncTask asyncTask;
    boolean isFirst=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        asyncTask = new MyAsyncTask();
        startView = (ImageView) findViewById(R.id.main_startBtn);
        pauseView = (ImageView) findViewById(R.id.main_pauseBtn);
        textView = (TextView) findViewById(R.id.main_textView);

        startView.setOnClickListener(this);
        pauseView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v == startView) {
            if(isFirst){
                asyncTask.isRun = true;
                asyncTask.execute();
                isFirst=false;
            }else{
                asyncTask.isRun=true;
            }

        } else if (v == pauseView) {
            asyncTask.isRun=false;

        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,String>{
         boolean loopFlag = true;
         boolean isRun;

        @Override
        protected String doInBackground(Void... voids) {
            int count = 10;
            while (loopFlag){
                try {
                    Thread.sleep(1000);
                    if ((isRun)){
                        count--;
                        publishProgress(count);
                        if(count==0){
                            loopFlag=false;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return "Finish!!";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            textView.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(String s) {
            textView.setText(s);
        }
    }
    class OneThread extends Thread{
        Handler oneHandler;

        @Override
        public void run() {
            Looper.prepare();
            oneHandler = new Handler(){
                @Override
                public void handleMessage(@NonNull Message msg) {
                    int data = msg.arg1;
                    if(msg.what==0){
                        Log.d("kkang","even data : "+data);
                    }else if(msg.what==1){
                        Log.d("kkang","odd data : "+data);
                    }
                }
            };
            Looper.loop();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        oneThread.oneHandler.getLooper().quit();
    }
    class TwoThread extends Thread{
        @Override
        public void run() {
            Random random = new Random();
            for(int i=0;i<10;i++){
                int data = random.nextInt(10);
                Message message = new Message();
                if(data%2==0){
                    message.what=0;
                }else{
                    message.what=1;
                }
                message.arg1=data;
                oneThread.oneHandler.sendMessage(message);
            }
        }
    }
}




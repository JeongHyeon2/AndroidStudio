package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText eTitle;
    private EditText eMessage;
    private Button channel1Btn;
    private NotificationHelper mNotificationhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eTitle = findViewById(R.id.edit_title);
        eMessage = findViewById(R.id.edit_message);
        channel1Btn = findViewById(R.id.btn_Channel1);

        mNotificationhelper = new NotificationHelper(this);

        channel1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = eTitle.getText().toString();
                String message = eMessage.getText().toString();
                sendOnChannel1(title, message);
            }
        });
    }

    public void sendOnChannel1(String title, String message){
        NotificationCompat.Builder nb = mNotificationhelper.getChannel1Notification(title, message);
        mNotificationhelper.getManager().notify(1, nb.build());
    }

}
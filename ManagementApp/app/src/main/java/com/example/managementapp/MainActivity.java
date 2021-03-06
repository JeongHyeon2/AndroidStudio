package com.example.managementapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity implements Network {

//    final Socket[] socket;
//    final OutputStream[] os;
//    final InputStream[] is;

    public MainActivity() throws IOException {
//        socket=new Socket[1];
//        os = new OutputStream[1];
//        is = new InputStream[1];

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        new Thread(() -> {
            try {
                socket[0] = new Socket("192.168.1.184", 3003);
                os[0] = socket[0].getOutputStream(); 
                is[0] = socket[0].getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText idText = findViewById(R.id.idText);
        EditText passwordText = findViewById(R.id.pwdText);
        Button loginButton = findViewById(R.id.loginBtn);
        TextView regiButton = (TextView) findViewById(R.id.registerBtn);

        regiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(() -> {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }).start();
            }
        });

        try {
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new Thread(() -> {
                        if(idText.getText().toString().equals("") || passwordText.getText().toString().equals("")){
                            Handler handler = new Handler(Looper.getMainLooper());
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "ID ?????? ??????????????? ??????????????????!", Toast.LENGTH_SHORT).show();
                                }
                            }, 0);
                            return;
                        }
                        Protocol protocol = new Protocol(Protocol.REQ_LOGIN);
                        protocol.setData("/" + idText.getText().toString() + "/" + passwordText.getText().toString() + "/");
                        try {
                            os[0].write(protocol.getPacket());
                            os[0].flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                    new Thread(() -> {
                        while (true) {
                            Protocol protocol = new Protocol(); // ??? Protocol ?????? ??????
                            byte[] buf = protocol.getPacket();
                            try {
                                is[0].read(buf);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            int packetType = buf[0]; // ?????? ??????????????? ?????? ?????? ??????

                            Handler handler = new Handler(Looper.getMainLooper());

                            switch (packetType) {
                                case Protocol.SUCCESS_LOGIN:

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "????????? ??????!", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 0);
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                    intent.putExtra("id", idText.getText().toString());
                                    startActivity(intent);
                                    break;
                                case Protocol.FAIL:
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "????????? ID ?????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 0);
                                    break;
                                default:
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {

                                            Toast.makeText(MainActivity.this, "?????? ???????????????.", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 0);
                            }
                        }
                    }).start();

                }
            });
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "?????????????????????.", Toast.LENGTH_SHORT).show();

        }
    }
}

package com.example.managementapp;

import java.net.*;
import java.io.*;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity implements Network {

    private boolean cnt = true;

    public RegisterActivity() throws IOException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            new Thread(() -> {
                try {
                    socket[0] = new Socket("192.168.0.6", 3003);
                    os[0] = socket[0].getOutputStream();
                    is[0] = socket[0].getInputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            EditText idText = findViewById(R.id.editTextTextPersonName);
            EditText passwordText = findViewById(R.id.editTextTextPersonName2);
            EditText nameText = findViewById(R.id.nameText);
            EditText ageText = findViewById(R.id.ageText);

            Button registerButton = findViewById(R.id.registerButton);
            registerButton.setOnClickListener(new View.OnClickListener() {
                Protocol protocol = new Protocol(Protocol.REQ_ADD);

                @Override
                public void onClick(View view) {
                    String id = idText.getText().toString();
                    String pwd = passwordText.getText().toString();
                    String name = nameText.getText().toString();
                    String age = ageText.getText().toString();
                    if(id.equals("")||pwd.equals("")||name.equals("")||age.equals("")){
                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegisterActivity.this, "빈칸이 있습니다!", Toast.LENGTH_SHORT).show();
                            }
                        }, 0);
                        return;
                    }
                    protocol.setData("/" + id + "/" + pwd + "/" + name + "/" + age + "/");
                    byte[] bf = protocol.getPacket();
                    new Thread(() -> {
                        try {
                            os[0].write(bf);
                            os[0].flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                            Protocol protocol = new Protocol(); // 새 Protocol 객체 생성
                            byte[] buf = protocol.getPacket();
                            try {
                                is[0].read(buf); // 클라이언트로부터 단어,정수 수신
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음
                            Handler handler = new Handler(Looper.getMainLooper());
                            switch (packetType) {
                                case Protocol.SUCCESS:

                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "계정 생성 성공!", Toast.LENGTH_SHORT).show();

                                        }
                                    }, 0);
                                    cnt = false;
                                    break;
                                case Protocol.DUP_ID:
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "중복된 ID 입니다.", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 0);
                                    break;
                                default:
                                    handler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(RegisterActivity.this, "서버 오류입니다!", Toast.LENGTH_SHORT).show();
                                        }
                                    }, 0);
                                    break;
                            }
                            if(!cnt) finish();
                    }).start();

                }
            });



        } catch (Exception e) {
            Toast.makeText(RegisterActivity.this, "서버 오류입니다!", Toast.LENGTH_SHORT).show();

        }

    }
}
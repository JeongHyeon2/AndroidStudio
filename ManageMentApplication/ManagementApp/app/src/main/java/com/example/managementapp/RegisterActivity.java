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


    public RegisterActivity() throws IOException {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

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
                    protocol.setData("/"+id + "/" + pwd+ "/" +name + "/" + age + "/");
                    byte[] bf = protocol.getPacket();
                    new Thread(() -> {
                        try {
                            os[0].write(bf);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }
            });
            new Thread(()-> {
                while (true) {
                    Protocol protocol = new Protocol(); // 새 Protocol 객체 생성
                    byte[] buf = protocol.getPacket();
                    try {
                        is[0].read(buf); // 클라이언트로부터 단어,정수 수신
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    int packetType = buf[0]; // 수신 데이터에서 패킷 타입 얻음

                    switch (packetType) {
                        case Protocol.SUCCESS:
//                            Handler handler = new Handler(Looper.getMainLooper());
//                            handler.postDelayed(new Runnable() {
//                                @Override
//                                public void run()
//                                {
//                                    Toast.makeText(MainActivity.this, "계정 생성 성공!", Toast.LENGTH_SHORT).show();
//                                }
//                            }, 0);
                            finish();
                            break;
                            case Protocol.DUP_ID:
                                Handler handler2 = new Handler(Looper.getMainLooper());
                                handler2.postDelayed(new Runnable() {
                                    @Override
                                    public void run()
                                    {
                                        Toast.makeText(RegisterActivity.this, "중복된 ID 입니다.", Toast.LENGTH_SHORT).show();
                                    }
                                }, 0);
                                break;
                        default:
                            Handler handler3 = new Handler(Looper.getMainLooper());
                            handler3.postDelayed(new Runnable() {
                                @Override
                                public void run()
                                {
                                    Toast.makeText(RegisterActivity.this, "서버 오류입니다.", Toast.LENGTH_SHORT).show();
                                }
                            }, 0);
                            break;
                    }
                }
            }).start();
        }catch (Exception e){

        }

    }
}
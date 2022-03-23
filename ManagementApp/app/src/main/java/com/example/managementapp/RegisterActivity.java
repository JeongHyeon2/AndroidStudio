package com.example.managementapp;
import java.net.*;
import java.io.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {



    public RegisterActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            final Socket[] socket = new Socket[1];
            final OutputStream[] os = new OutputStream[1];
            new Thread(() -> {
                try {
                    System.out.println("!!!");
                    socket[0] = new Socket("192.168.0.6", 3003);
                     os[0] = socket[0].getOutputStream();
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
        }catch (Exception e){

        }
    }
}
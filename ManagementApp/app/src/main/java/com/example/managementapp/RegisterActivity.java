package com.example.managementapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText idText = findViewById(R.id.idText);
        EditText passwordText = findViewById(R.id.pwdText);
        EditText nameText = findViewById(R.id.nameText);
        EditText ageText = findViewById(R.id.ageText);

        Button registerButton = findViewById(R.id.registerButton);
    }
}
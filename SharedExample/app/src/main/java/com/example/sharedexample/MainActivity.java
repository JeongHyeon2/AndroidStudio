package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_save;
    private String shared = "?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_save = findViewById(R.id.et_save);
        et_save.setText(shared);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        shared = et_save.getText().toString();

    }
}
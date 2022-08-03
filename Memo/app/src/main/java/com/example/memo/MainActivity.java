package com.example.memo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity{
    static int value_size =15;
    static EditText et_save;
    String shared = "";
    Button btn;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toolbar toolbar = findViewById(R.id.toolbar_first);
        setSupportActionBar(toolbar);


        setContentView(R.layout.activity_main);
        et_save= findViewById(R.id.etContents);
        btn = findViewById(R.id.button_save);
        imageView = findViewById(R.id.imageView2);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent = new Intent(MainActivity.this,PopUpActivity.class);
             startActivity(intent);
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("key","");
        value_size = sharedPreferences.getInt("val",15);
        et_save.setText(value);
        et_save.setTextSize(value_size);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String value = et_save.getText().toString();
                editor.putString("key", value);
                editor.putInt("val",value_size);
                editor.commit();
                Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
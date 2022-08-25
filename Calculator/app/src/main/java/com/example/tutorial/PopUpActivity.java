package com.example.tutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PopUpActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        editText = findViewById(R.id.et_prime);
        button = findViewById(R.id.btn_save);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    int num = Integer.parseInt(editText.getText().toString());
                    if (num > 10 || num < 0) {
                        Toast.makeText(PopUpActivity.this, "소수점 자리수는 0~10까지만 가능합니다!", Toast.LENGTH_SHORT).show();
                    } else {
                        MainActivity.prime = num;
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(PopUpActivity.this, "소수점 자리수는 0~10까지만 가능합니다!", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
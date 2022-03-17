package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addClick(View v) {
        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        TextView result = findViewById(R.id.result);
        try {
            double n1 =Double.parseDouble(number1.getText().toString());
            double n2 =Double.parseDouble(number2.getText().toString());
            result.setText((String.format("%.5f",n1 + n2)));
        } catch (Exception e) {
            result.setText("오류");
        }
    }

    public void subClick(View v) {
        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        TextView result = findViewById(R.id.result);
        try {
            double n1 =Double.parseDouble(number1.getText().toString());
            double n2 =Double.parseDouble(number2.getText().toString());
            result.setText((String.format("%.5f",n1 - n2)));
        } catch (Exception e) {
            result.setText("오류");
        }
    }

    public void mulClick(View v) {
        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        TextView result = findViewById(R.id.result);
        try {
            double n1 =Double.parseDouble(number1.getText().toString());
            double n2 =Double.parseDouble(number2.getText().toString());
            result.setText((String.format("%.5f",n1 * n2)));
        } catch (Exception e) {
            result.setText("오류");
        }
    }

    public void devClick(View v) {
        EditText number1 = findViewById(R.id.number1);
        EditText number2 = findViewById(R.id.number2);
        TextView result = findViewById(R.id.result);
        try {
            double n1 =Double.parseDouble(number1.getText().toString());
            double n2 =Double.parseDouble(number2.getText().toString());
            result.setText((String.format("%.5f",n1 / n2)));

        } catch (Exception e) {
            result.setText("오류");
        }
    }

}
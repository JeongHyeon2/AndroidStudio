package com.example.memotest;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopUpActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    static int value_popUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        textView=findViewById(R.id.textView5);

        seekBar = findViewById(R.id.seekBar3);
        seekBar.setProgress(EditMemo.value_size);
        textView.setText(String.valueOf(EditMemo.value_size));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                 value_popUp = seekBar.getProgress();
                EditMemo.content.setTextSize(value_popUp);
                textView.setText(String.valueOf(value_popUp));
                EditMemo.value_size = value_popUp;


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
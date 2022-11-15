package com.example.todolist;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class PopUpActivityForCreate extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Button btn_add,btn_calendar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_create);
        editText = findViewById(R.id.content);
        btn_add = findViewById(R.id.btn_add);
        btn_calendar = findViewById(R.id.btn_calendar);
        textView = findViewById(R.id.date);


        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String month,day;
                        if((monthOfYear+1)<10) month = "0"+(monthOfYear+1);
                        else month = String.valueOf(monthOfYear+1);

                        if((dayOfMonth)<10) day = "0"+dayOfMonth;
                        else day = String.valueOf(dayOfMonth);

                        textView.setText(year + "년 " + month + "월 " + day +"일");

                    }
                };
                LocalDate now = LocalDate.now();

                DatePickerDialog dialog = new DatePickerDialog(PopUpActivityForCreate.this, listener, now.getYear(), now.getMonthValue()-1,now.getDayOfMonth());
                dialog.show();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(textView.getText().toString().equals("날짜")) {
                    Toast.makeText(getApplicationContext(),"날짜를 선택해주세요!",Toast.LENGTH_SHORT).show();
                    return;
                }
               if( MainActivity.adapter.addItem(editText.getText().toString(),textView.getText().toString())) {
                   MainActivity.myDB.insertData(editText.getText().toString(), textView.getText().toString());

                   MainActivity.adapter.notifyDataSetChanged();
                   finish();
               }
               else{
                   Toast.makeText(getApplicationContext(),"중복된 날짜입니다!",Toast.LENGTH_SHORT).show();
               }
            }
        });
        }
}



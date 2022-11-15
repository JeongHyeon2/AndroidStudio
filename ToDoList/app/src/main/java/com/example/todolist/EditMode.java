package com.example.todolist;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;


public class EditMode extends AppCompatActivity {

    EditText editText;
    Button button, btn_calendar;
    TextView tv;
    String currentDate;
    String date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_popup);
        editText = findViewById(R.id.content);
        button = findViewById(R.id.btn_edit);
        tv = findViewById(R.id.tv_edit_popup);
        btn_calendar = findViewById(R.id.btn_calendar_edit);


        Intent intent = getIntent();
        editText.setText(intent.getStringExtra("content"));
        currentDate = intent.getStringExtra("date");
        tv.setText(currentDate + " 할 일");
        date = currentDate;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.adapter.deleteItem(currentDate);
                MainActivity.myDB.deleteData(currentDate);


                MainActivity.adapter.addItem(editText.getText().toString(),tv.getText().toString());
                MainActivity.myDB.insertData(editText.getText().toString(),tv.getText().toString());
                MainActivity.adapter.notifyDataSetChanged();

                Toast.makeText(getApplicationContext(), "수정완료", Toast.LENGTH_SHORT).show();
            }
        });
        btn_calendar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                        String month, day;
                        if ((monthOfYear + 1) < 10) month = "0" + (monthOfYear + 1);
                        else month = String.valueOf(monthOfYear + 1);

                        if ((dayOfMonth) < 10) day = "0" + dayOfMonth;
                        else day = String.valueOf(dayOfMonth);

                        date = year + month + day;
                        if(!date.equals(currentDate.replaceAll("[^0-9]",""))) {
                            for (int i = 0; i < MainActivity.adapter.listViewItemList.size(); i++) {
                                if (MainActivity.adapter.listViewItemList.get(i).getDate().equals(date)) {
                                    Toast.makeText(EditMode.this, "중복된 날짜입니다!", Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }
                        tv.setText(year + "년 " + month + "월 " + day + "일" + " 할 일");

                    }
                };
                LocalDate now = LocalDate.now();

                DatePickerDialog dialog = new DatePickerDialog(EditMode.this, listener, now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
                dialog.show();
            }
        });

    }


}

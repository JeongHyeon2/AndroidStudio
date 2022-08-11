package com.example.memotest;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class PopUpActivityForCreate extends AppCompatActivity {
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_create);


        editText = findViewById(R.id.et_title);
        btn = findViewById(R.id.btn_add);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editText.getText().toString().trim().equals("")) {
                    Toast.makeText(PopUpActivityForCreate.this, "제목을 입력해 주세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                for(int i=0;i < MainActivity.adapter.listViewItemList.size();i++) {
                    if(MainActivity.adapter.listViewItemList.get(i).getTitle().equals(editText.getText().toString().trim())) {
                        Toast.makeText(PopUpActivityForCreate.this, "이미 존재하는 메모입니다!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                Date time = new Date();
                SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH시 mm분 ss초");
                // 포맷 적용
                String now= format.format(time);
                MainActivity.adapter.addItem(editText.getText().toString(),"",now,ListViewAdapter.listViewItemList.size()+1);
                MainActivity.myDB.insertData(editText.getText().toString(),"",now,ListViewAdapter.listViewItemList.size()+1);
                finish();
            }
        });


    }

}

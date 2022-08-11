package com.example.memotest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EditMemo extends AppCompatActivity {

    static int value_size =15;
    TextView title;
    static EditText content;
    ImageView iv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memo);

        iv = findViewById(R.id.iv_setting);
        title=findViewById(R.id.tv_title);
        content=findViewById(R.id.et_content);
        btn = findViewById(R.id.button_save);


        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        content.setText(intent.getStringExtra("content"));


        content.setTextSize(value_size);

        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Date time = new Date();
                SimpleDateFormat format = new SimpleDateFormat ( "yyyy-MM-dd HH시 mm분 ss초");
                // 포맷 적용
                String now= format.format(time);
                String t = title.getText().toString();
                String c = content.getText().toString();
                MainActivity.myDB.updateData(t,c,now);
                MainActivity.adapter.updateItem(t,c,now);

                Toast.makeText(EditMemo.this,"저장완료",Toast.LENGTH_SHORT).show();


            }
        });

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditMemo.this,PopUpActivity.class);
                startActivity(intent);
            }
        });
    }


}

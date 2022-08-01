package com.example.memotest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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

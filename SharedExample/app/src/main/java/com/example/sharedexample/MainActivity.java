package com.example.sharedexample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;          // 프리퍼런스
    private SharedPreferences.Editor editor; // 에디터
    private EditText et_save;
    private String myStr;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        myStr = pref.getString("MyStr", "_");   // String 불러오기 (저장해둔 값 없으면 초기값인 _으로 불러옴)


        et_save = findViewById(R.id.et_save);
        btn = findViewById(R.id.btn_edit);


        et_save.setText(myStr);
//
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myStr = et_save.getText().toString();
//                editor.putString("MyStr", myStr);
//                editor.apply();
//                Toast.makeText(getApplicationContext(), "저장 완료", Toast.LENGTH_SHORT).show();
//
//            }
//        });


    }

    @Override
    protected void onPause(){
        super.onPause();
        myStr = et_save.getText().toString();
        editor.putString("MyStr", myStr);
        editor.apply();
    }

}
package com.example.memotest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    private ScrollView scrollView;
    private ListView listview;
    private ListViewAdapter adapter;
    EditText et_title, et_content;
    Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDB = new DatabaseHelper(this);

        // Adapter 생성
        adapter = new ListViewAdapter();
        et_content = findViewById(R.id.et_content);
        et_title = findViewById(R.id.et_title);
        btn_add = findViewById(R.id.btn_add);

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        scrollView = findViewById(R.id.main_box_scrollview);

        viewAll();

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem(et_title.getText().toString(), et_content.getText().toString());
                AddData();
                et_title.setText("");
                et_content.setText("");
                adapter.notifyDataSetChanged();
            }
        });

    }

    //데이터베이스 추가하기
    public void AddData() {
        boolean isInserted = myDB.insertData(et_title.getText().toString(),
                et_content.getText().toString());
        if (isInserted == true)
            Toast.makeText(MainActivity.this, "데이터추가 성공", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainActivity.this, "데이터추가 실패", Toast.LENGTH_SHORT).show();

    }

    // 데이터베이스 읽어오기
    public void viewAll() {
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            adapter.addItem(res.getString(1), res.getString(2));
        }
    }
}
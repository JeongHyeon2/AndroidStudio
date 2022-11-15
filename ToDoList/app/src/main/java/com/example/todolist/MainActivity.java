package com.example.todolist;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class MainActivity extends AppCompatActivity {
    static DatabaseHelper myDB;
    static  ListViewAdapter adapter;
    ConstraintLayout constraintLayout;
    FloatingActionButton fab;
    TextView today;
    Button btn;
    private SharedPreferences preferences;
    SharedPreferences.Editor editor ;
    private RecyclerView mRecyclerView;
    Intent serviceIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.alarmBtn);
        today = findViewById(R.id.today);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
        Date now = new Date();

        today.setText("오늘 날짜: "+sdf.format(now));
        mRecyclerView = findViewById(R.id.recyclerView);
        preferences = getSharedPreferences("size", MODE_PRIVATE);
        editor= preferences.edit();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this,AlertActivity.class);
               startActivity(intent);
            }
        });

        Button startBtnBtn = findViewById(R.id.start);
        startBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 시작",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                startService(intent);
            }
        });

        //서비스 취소
        Button stopBtnBtn = findViewById(R.id.end);
        stopBtnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Service 끝",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,MyService.class);
                stopService(intent);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        adapter = new ListViewAdapter();
        mRecyclerView.setAdapter(adapter);

        myDB = new DatabaseHelper(this);


        // 리스트뷰 참조 및 Adapter 달기
        constraintLayout = findViewById(R.id.constraintLayout);
        fab = findViewById(R.id.fab);


        viewAll();


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PopUpActivityForCreate.class);
                startActivity(intent);
            }
        });

    }


    // 데이터베이스 읽어오기
    public void viewAll() {
        Cursor res = myDB.getAllData();
        if (res.getCount() == 0) {
            return;
        }
        StringBuffer buffer = new StringBuffer();

        while (res.moveToNext()) {
            adapter.addItem(res.getString(1),res.getString(0));
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }


    @Override
    protected void onStop() {



        super.onStop();
    }

}

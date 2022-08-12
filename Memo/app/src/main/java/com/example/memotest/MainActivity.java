package com.example.memotest;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;

import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {
    static DatabaseHelper myDB;
    static  ListViewAdapter adapter;

    ConstraintLayout constraintLayout;
    FloatingActionButton fab;

    private SharedPreferences preferences;
    SharedPreferences.Editor editor ;

    private RecyclerView mRecyclerView;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerView);
        preferences = getSharedPreferences("size", MODE_PRIVATE);
        editor= preferences.edit();


        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(manager);

        adapter = new ListViewAdapter();
        mRecyclerView.setAdapter(adapter);


        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(adapter));

        mItemTouchHelper.attachToRecyclerView(mRecyclerView);

        myDB = new DatabaseHelper(this);


        // 리스트뷰 참조 및 Adapter 달기
        constraintLayout = findViewById(R.id.constraintLayout);
        fab = findViewById(R.id.fab);


        EditMemo.value_size =  preferences.getInt("text_size",15);

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



            adapter.addItem(res.getString(0), res.getString(1),res.getString(2),Integer.parseInt(res.getString(3)));
        }
    }

    @Override
    protected void onRestart() {
        adapter.notifyDataSetChanged();
        super.onRestart();
    }


    @Override
    protected void onStop() {

        editor.putInt("text_size",EditMemo.value_size);
        editor.commit();

        super.onStop();
    }
}

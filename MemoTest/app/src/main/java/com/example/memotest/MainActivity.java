package com.example.memotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    static DatabaseHelper myDB;
    private ScrollView scrollView;
    private ListView listview;
    static  ListViewAdapter adapter;
    ConstraintLayout constraintLayout;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDB = new DatabaseHelper(this);

        // Adapter 생성
        adapter = new ListViewAdapter();

//        et_title = findViewById(R.id.et_title);
//        btn_add = findViewById(R.id.btn_add);

        // 리스트뷰 참조 및 Adapter 달기
        listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
        scrollView = findViewById(R.id.main_box_scrollview);
        constraintLayout = findViewById(R.id.constraintLayout);
        fab = findViewById(R.id.fab);

        viewAll();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PopUpActivityForCreate.class);
                startActivity(intent);
                MainActivity.adapter.notifyDataSetChanged();
            }
        });

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Toast.makeText(MainActivity.this,adapter.listViewItemList.get(i).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, EditMemo.class);
                intent.putExtra("title",adapter.listViewItemList.get(i).getTitle());
                intent.putExtra("content",adapter.listViewItemList.get(i).getContent());
                startActivity(intent);
            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                scrollView.requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           int position, long id) {
                String title = adapter.listViewItemList.get(position).getTitle();

                // 이벤트 처리 종료 , 여기만 리스너 적용시키고 싶으면 true , 아니면 false
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("삭제")
                        .setMessage("\""+title+"\"을/를 삭제 하시겠습니까?")
                        .setIcon(android.R.drawable.ic_menu_delete)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                // 확인시 처리 로직
                                adapter.deleteItem(title);
                                myDB.deleteData(title);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(MainActivity.this,"삭제되었습니다",Toast.LENGTH_SHORT).show();
                            }})
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                            }})
                        .show();
                return true;
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
            adapter.addItem(res.getString(1), res.getString(2));
        }
    }
}
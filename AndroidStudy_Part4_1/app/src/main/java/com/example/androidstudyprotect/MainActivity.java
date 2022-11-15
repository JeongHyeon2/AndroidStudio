package com.example.androidstudyprotect;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telecom.Call;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    boolean callPermission;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            callPermission = true;
        }
        if(!callPermission){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},200);
        }
        listView = findViewById(R.id.listView);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_calllog",null);

        ArrayList<CallLog> data = new ArrayList<>();
        while(cursor.moveToNext()){

            data.add(new CallLog(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4)));
        }
        db.close();
        MyAdapter myAdapter = new MyAdapter(MainActivity.this,R.layout.item,data);
        listView.setAdapter(myAdapter);
    }

}
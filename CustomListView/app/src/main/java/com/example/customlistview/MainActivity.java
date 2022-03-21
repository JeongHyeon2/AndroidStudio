package com.example.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String [] items = {"A","B","C","D","E","F","G","H","I","J","K"};
        ListAdapter adapter = new ImageAdapter(this,items);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               String item = String.valueOf(adapterView.getItemAtPosition(i)); //adapterView에 있는 문자열을 담음.
               Toast.makeText(MainActivity.this,item,Toast.LENGTH_SHORT).show();
           }
       });
    }
}
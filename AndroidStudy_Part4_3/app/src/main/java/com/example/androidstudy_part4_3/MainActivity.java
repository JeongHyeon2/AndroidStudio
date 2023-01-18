package com.example.androidstudy_part4_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String data = intent.getStringExtra("key");
        startActivity(intent);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edittext);
        listView = findViewById(R.id.listview);
        ArrayList<SpannableStringBuilder> data = new ArrayList<>();
        ArrayList<String> base = new ArrayList<>();
        base.add("검색임");
        ArrayAdapter<SpannableStringBuilder> adapter = new ArrayAdapter<SpannableStringBuilder>(MainActivity.this,R.layout.item,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                editText.setText(data.get(i).toString());
            }
        });
    }
}
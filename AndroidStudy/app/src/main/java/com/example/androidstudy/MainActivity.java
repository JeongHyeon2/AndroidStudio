package com.example.androidstudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.content.res.ResourcesCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    SearchView searchView;
    ImageView iv;
    TextView textView;
    MyView myView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progress);
        myView=findViewById(R.id.myview);
        iv = findViewById(R.id.image);
        textView = findViewById(R.id.textview);
        registerForContextMenu(iv);
        ProgressThread progressThread = new ProgressThread();
        progressThread.start();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        myView.setOnChangeListener(new OnMyChangeListener() {
            @Override
            public void onChange(int value) {
                textView.setText("50");
            }
        });

    }
    class ProgressThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<1000;i++){
                SystemClock.sleep(1000);
                progressBar.incrementProgressBy(10);
                if(progressBar.getProgress()==100){
                    SystemClock.sleep(1000);
                    progressBar.setProgress(0);
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        //id 값으로 MenuItem 객체 획득
        MenuItem menuItem = menu.findItem(R.id.menu_main_search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint(getResources().getString(R.string.query_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                // 키보드에서 검색 버튼이 눌린 순간의 이벤트
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                return false;
            }


            @Override
            public boolean onQueryTextChange(String s) {
                // 검색 글이 한 자 한 자 입력 순간마다
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,0,0,"서버전송");
        menu.add(0,1,0,"보관함에 보관");
        menu.add(0,2,0,"삭제");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== 0){
            Toast.makeText(MainActivity.this,"서버전송",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId()==1){
            Toast.makeText(MainActivity.this,"보관함에 보관",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==2){
            Toast.makeText(MainActivity.this,"삭제",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()== android.R.id.home){
            Toast.makeText(MainActivity.this,"!!",Toast.LENGTH_SHORT).show();
            return true;
        }
        else if(item.getItemId()==R.id.menu1){
            Toast.makeText(MainActivity.this,"00000",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId()==R.id.menu2){
            Toast.makeText(MainActivity.this,"11111",Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}






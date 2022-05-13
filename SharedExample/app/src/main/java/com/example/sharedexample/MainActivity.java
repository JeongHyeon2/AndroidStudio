package com.example.sharedexample;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;          // 프리퍼런스
    private SharedPreferences.Editor editor; // 에디터
    private EditText et_save;
    private String myStr;
    private Button btn;
    Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ActionBar ac = getSupportActionBar();
//        ac.setTitle("회원가입");

//          myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setContentView(myToolbar);


        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        editor = pref.edit();

        myStr = pref.getString("MyStr", "_");   // String 불러오기 (저장해둔 값 없으면 초기값인 _으로 불러옴)


        et_save = findViewById(R.id.et_save);
//        btn = findViewById(R.id.btn_edit);


        et_save.setText(myStr);

    }

    @Override
    protected void onPause(){
        super.onPause();
        myStr = et_save.getText().toString();
        editor.putString("MyStr", myStr);
        editor.apply();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(getApplicationContext(), "환경설정 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }
    }
}
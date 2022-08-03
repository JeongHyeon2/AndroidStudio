package com.example.memo;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {
    EditText editText;
    Button btn_add;
    ArrayList<Memo> memoList;
    MemoDatabaseManager memoDatabaseManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        ListView listView = (ListView)findViewById(R.id.listView);
        final Adapter myAdapter = new Adapter(this,memoList);

        memoList.add(new Memo("test","test"));

        listView.setAdapter(myAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar_first);
        memoDatabaseManager = MemoDatabaseManager.getInstance(this);

        editText = findViewById(R.id.etContents);
        btn_add = findViewById(R.id.btn_add);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues addRowValue = new ContentValues();

                addRowValue.put("title", editText.getText().toString());

                memoDatabaseManager.insert(addRowValue);
            }
        });


    }
    public void getMovieData()
    {
        memoList = new ArrayList<>();

        String[] columns = new String[] {"_id", "title", "category", "grade"};

        Cursor cursor = memoDatabaseManager.query(columns, null, null,null,null,null);

        if(cursor != null)
        {
            while (cursor.moveToNext())
            {
                Memo currentData = new Memo();

                currentData.setTitle(cursor.getString(1));
                currentData.setContents(cursor.getString(2));
                memoList.add(currentData);
            }
        }
    }
}

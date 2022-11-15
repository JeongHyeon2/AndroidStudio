package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TODO.db"; // 데이터베이스 명
    public static final String TABLE_NAME = "todo_table"; // 테이블 명

    // 테이블 항목

    public static final String COL_1 = "date";
    public static final String COL_2 = "content";





    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(DATE TEXT PRIMARY KEY NOT NULL, CONTENT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String content, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        date = date.replaceAll("[^0-9]", "");

        contentValues.put(COL_1,date);
        contentValues.put(COL_2,content);
        System.out.println(contentValues.get("date") +", "+contentValues.get("content"));
        long result = db.insert(TABLE_NAME, null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }


    //데이터베이스 항목 읽어오기 Read
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }
    public Cursor getData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME +" WHERE date = '"+date+"'",null);
        return  res;
    }
    // 데이터베이스 삭제하기
    public Integer deleteData(String date){
        SQLiteDatabase db = this.getWritableDatabase();
       return db.delete(TABLE_NAME,"DATE = ?", new String[] { date.replaceAll("[^0-9]","")  });
    }

    public boolean updateData(String date, String content){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, date.replaceAll("[^0-9]",""));
        contentValues.put(COL_2,content);

        db.update(TABLE_NAME,contentValues,"DATE = ?", new String[] { date.replaceAll("[^0-9]","") });
        return true;
    }
}

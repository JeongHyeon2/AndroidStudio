package com.example.memotest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MEMO.db"; // 데이터베이스 명
    public static final String TABLE_NAME = "memo_table"; // 테이블 명

    // 테이블 항목
    public static final String COL_1 = "title";
    public static final String COL_2 = "content";
    public static final String COL_3 = "date";
    public static final String COL_4 = "pos";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(TITLE TEXT PRIMARY KEY , CONTENT TEXT , DATE TEXT , POS TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String content, String date, int pos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,title);
        contentValues.put(COL_2,content);
        contentValues.put(COL_3,date);
        contentValues.put(COL_4,pos);
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
    public Cursor getData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME +"WHERE = " +title,null);
        return  res;
    }
    // 데이터베이스 삭제하기
    public Integer deleteData(String title){
        SQLiteDatabase db = this.getWritableDatabase();
       // db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE title=" + title);
       return db.delete(TABLE_NAME,"TITLE = ?", new String[] { title });
    }

    //데이터베이스 수정하기
    public boolean updateData(String title, String content,String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,title);
        contentValues.put(COL_2,content);
        contentValues.put(COL_3,date);
        db.update(TABLE_NAME,contentValues,"TITLE = ?", new String[] { title });
        return true;
    }
    //데이터베이스 수정하기
    public boolean updateData(String title,int pos){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,title);
        contentValues.put(COL_4,pos);
        db.update(TABLE_NAME,contentValues,"TITLE = ?", new String[] { title });
        return true;
    }




}

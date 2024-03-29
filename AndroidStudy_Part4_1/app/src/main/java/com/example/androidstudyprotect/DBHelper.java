package com.example.androidstudyprotect;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=2;

    public DBHelper(Context context){
        super(context, "calldb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSql="create table tb_calllog ("+
                "_id integer primary key autoincrement," +
                "name not null," +
                "photo," +
                "date," +
                "phone)";

        db.execSQL(tableSql);

        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('홍길동','yes','휴대전화, 1일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('류현진','no','휴대전화, 1일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('강정호','no','휴대전화, 2일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('김현수','yes','휴대전화, 2일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('오승환','no','휴대전화, 2일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('이대호','no','휴대전화, 3일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('박병호','no','휴대전화, 3일전','010001')");
        db.execSQL("insert into tb_calllog (name, photo, date, phone) values ('추신수','no','휴대전화, 3일전','010001')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tb_calllog");
            onCreate(db);
        }
    }
}

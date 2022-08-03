package com.example.memo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemoDatabaseManager {
    static final String DB_MEMO = "Memo.db";   //DB이름
    static final String TABLE_MEMO = "Memos"; //Table 이름
    static final int DB_VERSION = 1;

    Context myContext = null;

    private static MemoDatabaseManager myDBManager = null;
    private SQLiteDatabase mydatabase = null;

    public static MemoDatabaseManager getInstance(Context context)
    {
        if(myDBManager == null)
        {
            myDBManager = new MemoDatabaseManager(context);
        }

        return myDBManager;
    }

    private MemoDatabaseManager(Context context)
    {
        myContext = context;

        //DB Open
        mydatabase = context.openOrCreateDatabase(DB_MEMO, context.MODE_PRIVATE,null);

        //Table 생성
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_MEMO +
                "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT);");
    }
    public long insert(ContentValues addRowValue)
    {
        return mydatabase.insert(TABLE_MEMO, null, addRowValue);
    }
    public Cursor query(String[] colums,
                        String selection,
                        String[] selectionArgs,
                        String groupBy,
                        String having,
                        String orderby)
    {
        return mydatabase.query(TABLE_MEMO,
                colums,
                selection,
                selectionArgs,
                groupBy,
                having,
                orderby);
    }
}

package com.example.memolistview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends CursorAdapter {

    public MyAdapter(Context context, Cursor c) {
        super(context, c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        //아이템 만들기
        return getLayoutInflater().inflate(R.layout.item,parent,false);
    }

    @Override
    public void bindView(View view, Context context, final Cursor cursor) {
        TextView txtcontent=view.findViewById(R.id.txtcontent);
        txtcontent.setText(cursor.getString(1));
        TextView txtwdate=view.findViewById(R.id.txtwdate);
        txtwdate.setText(cursor.getString(2));

        //ListView에 item을 생성했을때
        ImageView btndel=view.findViewById(R.id.btndel);

        //아이디값 가져오기
        final int _id=cursor.getInt(0);
        //삭제버튼 이벤트 설정
        btndel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //CONFRIM
                AlertDialog.Builder box=new AlertDialog.Builder(MainActivity.this);
                box.setMessage(_id+"을(를) 삭제하시겠습니까?");
                box.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sql="delete from memo where _id="+_id;
                        db.execSQL(sql);
                        //새로고침
                        onRestart();
                    }
                });
                box.setNegativeButton("닫기",null);
                box.show();
            }
        });
        ImageView btnupdate=view.findViewById(R.id.btnupdate);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,UpdateActivity.class);
                intent.putExtra("_id", _id);
                startActivity(intent);
            }
        });
    }
}

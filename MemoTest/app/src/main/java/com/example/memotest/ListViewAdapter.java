package com.example.memotest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private TextView titleTextView;
    private TextView contentTextView;

     ArrayList<Memo> listViewItemList = new ArrayList<Memo>();


    // ListViewAdapter의 생성자
    public ListViewAdapter() {


    }


    // Adapter에 사용되는 데이터의 개수를 리턴
    @Override
    public int getCount() {

        return listViewItemList.size();
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_memo_item, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        titleTextView = (TextView) convertView.findViewById(R.id.tv_memo_title);
        contentTextView = (TextView) convertView.findViewById(R.id.tv_memo_content);

        Memo listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView.setText(listViewItem.getTitle());
        contentTextView.setText(listViewItem.getContent());

        return convertView;
    }
    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수.
    public void addItem(String title, String content) {
        Memo item = new Memo();
        item.setTitle(title);
        item.setContent(content);
        listViewItemList.add(item);
    }
    // 아이템 데이터 추가를 위한 함수.
    public void deleteItem(String title) {

        for(int i=0;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getTitle().equals(title)){
                listViewItemList.remove(i);
            }
        }
    }
    public void updateItem(String title,String content){

    }

}

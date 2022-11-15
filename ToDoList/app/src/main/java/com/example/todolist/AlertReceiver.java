package com.example.todolist;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import androidx.core.app.NotificationCompat;

import java.util.ArrayList;

public class AlertReceiver extends BroadcastReceiver {
    ArrayList<ToDo> listViewItemList;
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);

        NotificationCompat.Builder nb = notificationHelper.getChannelNotification(getTodoList());
        notificationHelper.getManager().notify(1,nb.build());
    }
    private String getTodoList(){
        listViewItemList = new ArrayList<>();

        Cursor res = MainActivity.myDB.getAllData();
        if (res.getCount() == 0) {

            return null;
        }

        StringBuffer sb = new StringBuffer();
        while (res.moveToNext()) {
            addItem(res.getString(1),res.getString(0));
        }

        for(int i=0;i<listViewItemList.size();i++){
            String date = listViewItemList.get(i).getDate();
            sb.append(date.substring(0,4));
            sb.append("년 ");
            sb.append(date.substring(4,6));
            sb.append("월 ");
            sb.append(date.substring(6,8));
            sb.append("일까지 ");
            sb.append(listViewItemList.get(i).getContent());
           sb.append("\n");
        }

        return sb.toString();
    }
    void addItem(String content,String date) {

        ToDo item = new ToDo();
        item.setContent(content);
        item.setDate(date);
        int i = 0;
        int size = listViewItemList.size();
        if(size == 0 )   {
            listViewItemList.add(item);
            return;
        }

        for(;i<size;i++){
            if(listViewItemList.get(i).getDate().compareTo(item.getDate())>0) {
                listViewItemList.add(i,item);
                return;

            }
        }
        if(i==size) listViewItemList.add(i,item);


    }
}
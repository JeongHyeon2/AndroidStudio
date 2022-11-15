package com.example.todolist;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemViewHolder> {
    static ArrayList<ToDo> listViewItemList  = new ArrayList<>();
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_todo_item, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.onBind(listViewItemList.get(position),position);
    }

    @Override
    public int getItemCount() {
        return listViewItemList.size();
    }

    public void setItems(ArrayList<ToDo> itemList){
        listViewItemList = itemList;

    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView content,date ,dDay;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.tv_memo_content);
            date = itemView.findViewById(R.id.tv_date);
            dDay = itemView.findViewById(R.id.dDay);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyDataSetChanged();
                }
            });
            content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(),EditMode.class);
                    intent.putExtra("content",content.getText().toString());
                    intent.putExtra("date",date.getText().toString());
                    itemView.getContext().startActivity(intent);

                }
            });
            date.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("삭제")
                            .setMessage("이 일정을 삭제 하시겠습니까?")
                            .setIcon(android.R.drawable.ic_menu_delete)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // 확인시 처리 로직
                                    String dateStr = date.getText().toString().replaceAll("[^0-9]", "");
                                    MainActivity.adapter.deleteItem(dateStr);
                                    MainActivity.myDB.deleteData(dateStr);
                                    notifyDataSetChanged();
                                    Toast.makeText(itemView.getContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                                }})
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }})
                            .show();
                    return true;
                }
            });
            content.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("삭제")
                            .setMessage("이 일정을 삭제 하시겠습니까?")
                            .setIcon(android.R.drawable.ic_menu_delete)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // 확인시 처리 로직
                                    String dateStr = date.getText().toString().replaceAll("[^0-9]", "");
                                    MainActivity.adapter.deleteItem(dateStr);
                                    MainActivity.myDB.deleteData(dateStr);
                                    notifyDataSetChanged();
                                    Toast.makeText(itemView.getContext(),"삭제되었습니다",Toast.LENGTH_SHORT).show();
                                }})
                            .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                }})
                            .show();
                    return true;
                }
            });


        }
        public void onBind(ToDo item,int position){
            content.setText(item.getContent());

            String year = item.getDate().substring(0,4);
            String month = item.getDate().substring(4,6);
            String day = item.getDate().substring(6,8);

            date.setText(year+"년 "+month+"월 "+day+"일");

            dDay.setText("남은 날짜: "+calcdate(Integer.parseInt(year),Integer.parseInt(month),Integer.parseInt(day))+"일");
        }
    }
    public int calcdate(int _year, int _month, int _day) {
        try {
            TimeZone tz = TimeZone.getTimeZone ("Asia/Seoul");
            Calendar today = Calendar.getInstance (tz);
            Calendar dday = Calendar.getInstance(tz);

            dday.set(_year, _month-1, _day);

            long cnt_dday = dday.getTimeInMillis() / 86400000;
            long cnt_today = today.getTimeInMillis() / 86400000;
            long sub = cnt_dday - cnt_today;

            return (int) sub;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    // 아이템 데이터 추가를 위한 함수.
    boolean addItem(String content,String date) {
        for(int i=0;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getDate().equals(date.replaceAll("[^0-9]", ""))) return false;
        }
        ToDo item = new ToDo();
        item.setContent(content);
        item.setDate(date.replaceAll("[^0-9]", ""));
        if(listViewItemList.size() == 0 )   {
            listViewItemList.add(item);
            return true;
        }
        int i = 0;
        for(;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getDate().compareTo(item.getDate())>0) {
                listViewItemList.add(i,item);
                return true;
            }
        }
        if(i==listViewItemList.size()) listViewItemList.add(i,item);
        return true;

    }
    // 아이템 데이터 추가를 위한 함수.
    public void deleteItem(String date) {
        String str = date.replaceAll("[^0-9]","");
        for(int i=0;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getDate().equals(str)){
                listViewItemList.remove(i);
            }
        }

    }

    public void updateItem(String date,String content){
        for(int i=0;i<listViewItemList.size();i++){
            if(date.replaceAll("[^0-9]","").equals(listViewItemList.get(i).getDate())){
                listViewItemList.get(i).setContent(content);
            }
        }
    }

}

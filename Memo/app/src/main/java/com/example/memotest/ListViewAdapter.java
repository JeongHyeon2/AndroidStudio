package com.example.memotest;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabItem;

import java.util.ArrayList;
import java.util.Collections;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ItemViewHolder> implements ItemTouchHelperListener {
    static ArrayList<Memo> listViewItemList  = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_memo_item, parent, false);

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

    public void setItems(ArrayList<Memo> itemList){
        listViewItemList = itemList;

    }

    @Override
    public boolean onItemMove(int form_position, int to_position) {
        Memo item = listViewItemList.get(form_position);
        Memo item2 = listViewItemList.get(to_position);

        listViewItemList.set(form_position,item2);
        listViewItemList.set(to_position,item);

        item.setPos(to_position);
        item2.setPos(form_position);

        notifyItemMoved(form_position, to_position);

       MainActivity.myDB.updateData(item2.getTitle(),form_position);
       MainActivity.myDB.updateData(item.getTitle(),to_position);

        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        listViewItemList.remove(position);
        notifyItemRemoved(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView title,content,date;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.tv_memo_title);
            content = itemView.findViewById(R.id.tv_memo_content);
            date = itemView.findViewById(R.id.tv_date);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(itemView.getContext(),EditMemo.class);
                    intent.putExtra("title",title.getText().toString());
                    intent.putExtra("content",content.getText().toString());
                    itemView.getContext().startActivity(intent);
                    notifyDataSetChanged();
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                // 이벤트 처리 종료 , 여기만 리스너 적용시키고 싶으면 true , 아니면 false
                    new AlertDialog.Builder(itemView.getContext())
                            .setTitle("삭제")
                            .setMessage("\""+title.getText().toString()+"\"을/를 삭제 하시겠습니까?")
                            .setIcon(android.R.drawable.ic_menu_delete)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    // 확인시 처리 로직
                                    MainActivity.adapter.deleteItem(title.getText().toString());
                                    MainActivity.myDB.deleteData(title.getText().toString());
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
        public void onBind(Memo item,int position){

            title.setText(item.getTitle());
            content.setText(item.getContent());
            date.setText(item.getDate());
            item.setPos(position);

        }



    }

    // 아이템 데이터 추가를 위한 함수.
    public void addItem(String title, String content,String date,int pos) {
        Memo item = new Memo();
        item.setTitle(title);
        item.setContent(content);
        item.setDate(date);
        item.setPos(pos);
        listViewItemList.add(item);
       Collections.sort(listViewItemList, Memo::compareTo);

    }
    // 아이템 데이터 추가를 위한 함수.
    public void deleteItem(String title) {

        for(int i=0;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getTitle().equals(title)){
                listViewItemList.remove(i);
            }
        }
    }

    public void updateItem(String title,String content,String date){

        for(int i=0;i<listViewItemList.size();i++){
            if(listViewItemList.get(i).getTitle().equals(title)){
                if(listViewItemList.get(i).getContent().equals(content)) return;
                System.out.println(listViewItemList.get(i).getTitle());
                System.out.println(content);

                listViewItemList.get(i).setTitle(title);
                listViewItemList.get(i).setContent(content);
                listViewItemList.get(i).setDate(date);

            }

        }
        Cursor res = MainActivity.myDB.getData(title);
        MainActivity.adapter.notifyItemChanged(Integer.parseInt(res.getString(3)));

    }
}

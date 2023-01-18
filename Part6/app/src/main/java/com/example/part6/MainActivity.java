package com.example.part6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        List<String> list = new ArrayList<>();
        list.add("item1");
        list.add("item2");
        list.add("item3");
        list.add("item4");
        list.add("item5");
        list.add("item6");
        list.add("item7");
        list.add("item8");
        list.add("item9");
        list.add("item10");

        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MyItemDecoration());
        recyclerView.setAdapter(new MyAdapter(list));


    }
}

class MyViewHolder extends RecyclerView.ViewHolder{
    public TextView title;
    public MyViewHolder(View itemView){
        super(itemView);
        title = itemView.findViewById(android.R.id.text1);
    }
}

class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
    private List<String> list;
    public MyAdapter(List<String> list){
        this.list=list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
          /*항목을 구성하기 위한 layout xml 파일 inflate
    이곳의 리턴값은 inflate된 view의 계층구조에서
     view를 findViewByID 할 ViewHolder 리턴
     */
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        return new MyViewHolder(view);
    }

    // 각 항목을 구성하기 위해서 호출
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        String text = list.get(i);
        myViewHolder.title.setText(text);
    }
    // 항목 개수
    @Override
    public int getItemCount() {
        return list.size();
    }
}
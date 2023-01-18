package com.example.mission1;

import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class OneFragment extends Fragment {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView=(RecyclerView)inflater.inflate(R.layout.fragment_one, container, false);

        List<String> list=new ArrayList<>();
        for(int i=0; i<20; i++){
            list.add("Item="+i);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyAdapter(list));
        recyclerView.addItemDecoration(new MyItemDecoration());

        return recyclerView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public MyViewHolder(View itemView){
            super(itemView);
            title=(TextView)itemView.findViewById(android.R.id.text1);
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
        private final List<String> list;
        public MyAdapter(List<String> list){
            this.list=list;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String text=list.get(position);
            holder.title.setText(text);
        }

        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    private class MyItemDecoration extends RecyclerView.ItemDecoration {
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(20, 20, 20, 20);
            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view, 20.0f);
        }
    }
}

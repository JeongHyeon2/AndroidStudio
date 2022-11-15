package com.example.androidstudyprotect;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<CallLog> {
    Context context;
    int resId;
    ArrayList<CallLog> data;
    public MyAdapter(@NonNull Context context, int resource, ArrayList<CallLog> data) {
        super(context, resource, data);
        this.context = context;
        resId=resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            MyViewHolder holder = new MyViewHolder(convertView);
            convertView.setTag(holder); //저장
        }
        CallLog callLog = data.get(position);
        MyViewHolder holder = (MyViewHolder) convertView.getTag(); //획득
        holder.tv_name.setText(callLog.name);
        holder.tv_content.setText(callLog.date);
        if(callLog.photo.equals("yes")){
            holder.iv_profile.setImageResource(R.drawable.hong);
        }else{
            holder.iv_profile.setImageResource(R.drawable.ic_person);
        }
        holder.iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:"+callLog.phone));
                context.startActivity(intent);
            }
        });


        return convertView;
    }
}

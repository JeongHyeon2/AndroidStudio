package com.example.androidstudy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DriveAdapter extends ArrayAdapter<DriveV0> {

    Context context;
    int resId;
    ArrayList<DriveV0> data;
    public DriveAdapter(@NonNull Context context, int resource, ArrayList<DriveV0> data) {
        super(context, resource, data);
        this.context = context;
        resId=resource;
        this.data = data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
            DriveHolder holder = new DriveHolder(convertView);
            convertView.setTag(holder);
        }
        DriveHolder holder = (DriveHolder) convertView.getTag();
        holder.textView.setText("TEST");
        return convertView;
    }
}

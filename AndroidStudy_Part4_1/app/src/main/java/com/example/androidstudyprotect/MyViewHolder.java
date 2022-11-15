package com.example.androidstudyprotect;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MyViewHolder {
    public ImageView iv_profile,iv_phone;
    public TextView tv_name,tv_content;
    public MyViewHolder(View root){
        iv_phone = root.findViewById(R.id.iv_phone);
        iv_profile = root.findViewById(R.id.iv_profile);
        tv_content = root.findViewById(R.id.tv_content);
        tv_name = root.findViewById(R.id.tv_name);
    }
}

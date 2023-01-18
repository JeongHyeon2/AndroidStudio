//package com.example.mission3;
//
//import android.graphics.drawable.GradientDrawable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//
//public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    ArrayList<Item> list;
//    public MyAdapter(ArrayList<Item> list){
//        this.list = list;
//    }
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        if (viewType == Item.TYPE_HEADER){
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_header, parent, false);
//            return new MyHeaderViewHolder(view);
//        }else {
//            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mission3_item_data, parent, false);
//            return new MyViewHolder(view);
//        }
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Item item = list.get(position);
//        if(item.getType() == Item.TYPE_DATA){
//            MyViewHolder myViewHolder = (MyViewHolder) holder;
//            Person person = (Person) item;
//            myViewHolder.date.setText( person.date);
//            myViewHolder.name.setText(person.name);
//
//            int count = position % 5;
//            if(count == 0){
//                ((GradientDrawable)myViewHolder.imageView.getBackground()).setColor(0xff009688);
//            }else if(count==1){
//                ((GradientDrawable)myViewHolder.imageView.getBackground()).setColor(0xff4285f4);
//            }else if(count==2){
//                ((GradientDrawable)myViewHolder.imageView.getBackground()).setColor(0xff039be5);
//            }else if(count==3){
//                ((GradientDrawable)myViewHolder.imageView.getBackground()).setColor(0xff9c27b0);
//            }else if(count==4){
//                ((GradientDrawable)myViewHolder.imageView.getBackground()).setColor(0xff0097a7);
//            }
//
//        }else{
//            MyHeaderViewHolder myHeaderViewHolder = (MyHeaderViewHolder) holder;
//            Header header = (Header) item;
//            myHeaderViewHolder.head.setText(header.headerTitle);
//
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//}
//
//class MyViewHolder extends RecyclerView.ViewHolder{
//    public TextView name,date;
//    public ImageView imageView;
//
//    public MyViewHolder(View itemView){
//        super(itemView);
//        name = itemView.findViewById(R.id.mission3__item_person);
//        date = itemView.findViewById(R.id.mission3_item_date);
//        imageView = itemView.findViewById(R.id.mission3_item_dialer);
//    }
//}
//class MyHeaderViewHolder extends RecyclerView.ViewHolder{
//
//    public TextView head;
//    public MyHeaderViewHolder(@NonNull View itemView) {
//        super(itemView);
//        head = itemView.findViewById(R.id.mission3_item_header);
//    }
//}

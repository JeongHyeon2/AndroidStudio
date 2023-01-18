package com.example.part6;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemDecoration extends RecyclerView.ItemDecoration {
    public MyItemDecoration() {
        super();
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        //항목의 index 값 획득
        int index = parent.getChildAdapterPosition(view)+1;

        if(index%3==0){
            //20 픽셀 씩 여백
            outRect.set(20,20,20,60);
        }else{
            outRect.set(20,20,20,20);
        }
        view.setBackgroundColor(0xFFECE9E9);
        ViewCompat.setElevation(view,20.0f);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();
        Paint paint = new Paint();
        paint.setColor(Color.RED);
        c.drawRect(0,0,width/3,height,paint);
    }



    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();
        Drawable dr = ResourcesCompat.getDrawable(,R.drawable.android,null);
    }
}

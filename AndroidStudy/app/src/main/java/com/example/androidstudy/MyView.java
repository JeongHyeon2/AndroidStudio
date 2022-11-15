package com.example.androidstudy;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyView extends View {
    Context context;
    int color;
    String [] attrArr;
    ArrayList<OnMyChangeListener> listeners = new ArrayList<>();
    int size ;
    int strokeWidth;
    int position ;
    public MyView(Context context) {
        super(context);
    }
    public void setOnChangeListener(OnMyChangeListener listener){
        listeners.add(listener);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int value = 0;
        // 데이터 변경
        value++;
        // 화면 갱신
        invalidate();
        for (OnMyChangeListener listener : listeners) {
            // observer에 데이터 전달
            listener.onChange(value);
        }
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = 0, height = 0;
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        if(heightMode==MeasureSpec.AT_MOST){
            height= size+strokeWidth;
        }else if(heightMode==MeasureSpec.EXACTLY){
            height=heightSize;
        }

        if(widthMode==MeasureSpec.AT_MOST){
            width= size+strokeWidth;
        }else if(widthMode==MeasureSpec.EXACTLY){
            width=widthSize;
        }
        setMeasuredDimension(width,height);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        size =  context.getResources().getDimensionPixelSize(R.dimen.size);
        strokeWidth = context.getResources().getDimensionPixelSize(R.dimen.strokeWidth);
        position = context.getResources().getDimensionPixelSize(R.dimen.position);
        if(attrs != null){
            TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
            color = a.getColor(R.styleable.MyView_customColor,Color.RED);
        }
        attrArr = new String[attrs.getAttributeCount()];
        for(int i=0; i<attrs.getAttributeCount();i++){
            attrArr[i]=attrs.getAttributeName(i)+"="+attrs.getAttributeValue(i);
        }
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN));

        Paint paint = new Paint();
        paint.setStrokeWidth(strokeWidth);
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);

        RectF rectF = new RectF(position,position,size,size);
        canvas.drawRect(rectF,paint);
        super.onDraw(canvas);
    }
}

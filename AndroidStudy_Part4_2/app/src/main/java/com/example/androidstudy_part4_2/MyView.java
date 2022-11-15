package com.example.androidstudy_part4_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {

    Context context;
    int value, size, strokeSize, textSize, width, height;

    public MyView(Context context) {
        super(context);
        this.context = context;
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN));
        RectF rectF = new RectF(20,20,size-20,size-20);

        Paint paint = new Paint();
        paint.setColor(Color.LTGRAY);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeSize);

        canvas.drawArc(rectF,0,360,false,paint);

        paint.setColor(Color.RED);
        paint.setStrokeJoin(Paint.Join.ROUND);

        canvas.drawArc(rectF,-90,value,false,paint);

        paint.setTextSize(textSize);
        paint.setStrokeWidth(5);

        String txt = String.valueOf(value);
        int xPos = width/2 - (int)(paint.measureText(txt)/2);
        int yPos = (int)(height/2-((paint.descent()+paint.ascent())/2));
        canvas.drawText(txt,xPos,yPos,paint);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 커스텀 뷰 크기 획득
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width,height);
    }

    private void init(){
        size=getResources().getDimensionPixelSize(R.dimen.donut_size);
        strokeSize=getResources().getDimensionPixelSize(R.dimen.donut_stroke_size);
        textSize=getResources().getDimensionPixelSize(R.dimen.donut_textSize);

    }
    public void setValue(int value){
        this.value = value;
        invalidate();
    }
}

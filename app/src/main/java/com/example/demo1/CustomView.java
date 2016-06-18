package com.example.demo1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import java.util.Random;

/**
 * Created by Administrator on 2016/6/18.
 */
public class CustomView extends View{

    private String mTitle;
    private int mTitleSize;
    private int mTitleColour;
    private Paint mPaint;

    private Rect mBound;
    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray arr = context.getTheme().obtainStyledAttributes(attrs,R.styleable.CustomView,defStyleAttr,0);
        int n = arr.getIndexCount();
        for(int i=0;i<n;i++)
        {
            int a = arr.getIndex(i);

            switch(a)
            {
                case R.styleable.CustomView_titleText:
                    mTitle=arr.getString(a);
                    break;
                case R.styleable.CustomView_titleSize:
                    int defVal=(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,16,getResources().getDisplayMetrics());
                    mTitleSize=arr.getDimensionPixelSize(a,(int) TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_SP, 16, getResources().getDisplayMetrics()));
                    break;
                case R.styleable.CustomView_titleTextColour:
                    mTitleColour=arr.getColor(a, Color.BLACK);
                    break;
            }
        }


        arr.recycle();

        mPaint = new Paint();
        mPaint.setTextSize(mTitleSize);
        mBound = new Rect();
        mPaint.getTextBounds(mTitle, 0, mTitle.length(), mBound);
    }


    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
         this(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       // super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;
        if(widthMode==MeasureSpec.EXACTLY)
        {
            width=widthSize;
        }else
        {
//            mPaint.setTextSize(mTitleSize);
//            mPaint.getTextBounds();
           width= mBound.width()+getPaddingLeft()+getPaddingRight();
        }
        if(heightMode== MeasureSpec.EXACTLY)
        {
            height=heightSize;

        }else
        {
            height=mBound.height()+getPaddingTop()+getPaddingBottom();
        }
        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.YELLOW);
        canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), mPaint);
        mPaint.setColor(mTitleColour);
        canvas.drawText(mTitle, getWidth() / 2 - mBound.width() / 2, getHeight() / 2 + mBound.height() / 2, mPaint);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


    public void randomText()
    {
        Random random = new Random(System.currentTimeMillis());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<4;i++)
        {
           int a =  random.nextInt(10);
            sb.append(a);
        }
        mTitle=sb.toString();
    }

}

package com.innovator.customviewtest;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by innovator on 2018/3/19.
 */

public class CircleView extends View {

    private int mColor = Color.YELLOW;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CircleView(Context context) {
        super(context);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.CircleView);
        mColor = typedArray.getColor(R.styleable.CircleView_circle_color,Color.BLUE);
        typedArray.recycle();
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        //获取宽度的模式和测量大小
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);

        //获取高度的模式和测量大小
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,200);
            return;
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSpecSize);
            return;
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,200);
            return;
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 重写此方法，实现不规则的效果，注意处理padding
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingbottom = getPaddingBottom();
        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingbottom;
        int radius = Math.min(width,height) / 2;
        canvas.drawCircle(paddingLeft+ width/2,paddingTop +height/2,radius,mPaint);
    }
}

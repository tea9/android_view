package com.demo.android_view.mview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.demo.android_view.R;

/**
 * created by tea9 at 2018/11/15
 * 小竖线
 */
public class VerticalBarView extends View {

    private Paint paint;
    private int line_width;
    private float line_height;
    private float margin;
    private float startY,startX;
    private int line_color;

    public VerticalBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initVariable();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.VerticalBarView);
        line_width = (int)typedArray.getDimension(R.styleable.VerticalBarView_line_width,dp2px(5));
        line_height = typedArray.getDimension(R.styleable.VerticalBarView_line_height,dp2px(15));
        margin = typedArray.getDimension(R.styleable.VerticalBarView_line_margin,dp2px(10));
        line_color = typedArray.getColor(R.styleable.VerticalBarView_line_color,Color.parseColor("#10C252"));
    }

    private void initVariable() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(line_color);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(line_width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startY = margin+(line_width/2);
        startX = margin+(line_width/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //float startX, float startY, float stopX, float stopY
        float lineHeight = startY+line_height;
//        Log.e("shaomiao","startX"+startX); //startX37.0
//        Log.e("shaomiao","startY"+startY); //startY37.0
//        Log.e("shaomiao","lineHeight"+lineHeight);  //lineHeight82.0

        canvas.drawLine(startX,startY,startX,lineHeight,paint);
//        canvas.drawLine(110,100,110,20,paint);
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }
}

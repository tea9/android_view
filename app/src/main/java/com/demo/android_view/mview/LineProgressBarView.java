package com.demo.android_view.mview;

import android.animation.ValueAnimator;
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
 * 横线进度
 * type 0 按照整个是100
 * type 1 按照整个是两个100
 * TODO 处理0的问题
 */
public class LineProgressBarView extends View {

    private Context context;
    private int background_color, color_a, color_b, margin; // margin 左右边距
    private float bar_height, bar_width;
    private float num_a, num_b;
    private Paint back_paint, num_a_paint, num_b_paint;
    private int width, height;
    private int type;
    // 线的X起始左标
    private float startX;
    private float endX;

    public LineProgressBarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getAtt(attrs);
        initPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 硬件加速
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineProgressBarView);
        background_color = typedArray.getColor(R.styleable.LineProgressBarView_background_color, Color.parseColor("#E3E2E2"));
        color_a = typedArray.getColor(R.styleable.LineProgressBarView_color_a, Color.parseColor("#FF6059"));
        color_b = typedArray.getColor(R.styleable.LineProgressBarView_color_b, Color.parseColor("#41CFFF"));
        bar_height = typedArray.getDimension(R.styleable.LineProgressBarView_bar_height, 30f);
        bar_width = typedArray.getDimension(R.styleable.LineProgressBarView_bar_width, 0);
        margin = typedArray.getInteger(R.styleable.LineProgressBarView_margin, dp2px(30));
        num_a = typedArray.getFloat(R.styleable.LineProgressBarView_num_a, 40);
        num_b = typedArray.getFloat(R.styleable.LineProgressBarView_num_b, 40);
        type = typedArray.getInteger(R.styleable.LineProgressBarView_bar_type, 0);
        typedArray.recycle();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    protected float dp2px(float dpVal) {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    private void initPaint() {
        back_paint = getPaint(Paint.Style.FILL, background_color, bar_height);
        back_paint.setStrokeCap(Paint.Cap.ROUND);

        num_a_paint = getPaint(Paint.Style.FILL, color_a, bar_height);
        num_a_paint.setColor(color_a);
        num_b_paint = getPaint(Paint.Style.FILL, color_b, bar_height);
        num_b_paint.setColor(color_b);
    }

    private Paint getPaint(Paint.Style style, int color, float width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿标志
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        startX = margin;
        endX = width - margin;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Log.e("shaomiao","startX:"+startX);
//        Log.e("shaomiao","endX:"+endX);
//        Log.e("shaomiao","width:"+width);
//        Log.e("shaomiao","height:"+height);
//        Log.e("shaomiao","num_a:"+num_a);
//        Log.e("shaomiao","num_b:"+num_b);
        canvas.drawLine(startX, height / 2, endX, height / 2, back_paint); // 背景线
        if (type == 0) {

            // -><-
            // 从两边往中间画
            float length_a = (endX * num_a / 100) + startX - (num_a / 100 * (startX));
            float length_b = (startX * num_b / 100) + endX - (num_b / 100 * (endX));
//            Log.e("shaomiao","lenth:"+length_a);
//            Log.e("shaomiao","lenthb:"+length_b);
            num_a_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_a > 0)
                canvas.drawLine(startX, height / 2, startX + 2, height / 2, num_a_paint);
            num_a_paint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(startX, height / 2, length_a, height / 2, num_a_paint);
            num_b_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_b > 0)
                canvas.drawLine(endX, height / 2, endX - 2, height / 2, num_b_paint);
            num_b_paint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(endX, height / 2, length_b - 2, height / 2, num_b_paint);
        } else if (type == 1) {
            //<-|->
            // 从中间往两边画
            float centre = (endX - startX) / 2 + margin;
            float length_a = (startX * num_a / 100) + centre - (num_a / 100 * (centre));
            float length_b = (endX * num_b / 100) + centre - (num_b / 100 * (centre));
//            Log.e("shaomiao","centre:"+centre);
//            Log.e("shaomiao","length_a1:"+length_a);
//            Log.e("shaomiao","length_b1:"+length_b);
//            num_a_paint.setStrokeCap(Paint.Cap.BUTT);
//            num_b_paint.setStrokeCap(Paint.Cap.BUTT);
//            canvas.drawLine(centre, height / 2, centre+10, height / 2, num_b_paint);
//            canvas.drawLine(endX, height / 2, endX-10, height / 2, num_a_paint);
//            canvas.drawLine(startX, height / 2, startX+10, height / 2, num_a_paint);
            num_a_paint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(centre, height / 2, length_a, height / 2, num_a_paint);
            num_a_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_a > 0)
                canvas.drawLine(length_a + 2, height / 2, length_a, height / 2, num_a_paint);
            num_b_paint.setStrokeCap(Paint.Cap.BUTT);
            canvas.drawLine(centre, height / 2, length_b, height / 2, num_b_paint);
            num_b_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_b > 0)
                canvas.drawLine(length_b, height / 2, length_b + 2, height / 2, num_b_paint);
        }

    }

    public void setNumAProgress(final int xx) {
        final ValueAnimator anim = ValueAnimator.ofFloat(0, xx);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                num_a = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }

    public void setNumBProgress(final int xx) {
        final ValueAnimator anim = ValueAnimator.ofFloat(0, xx);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                num_b = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }

    public void setType(int type) {
        this.type = type;
        invalidate();
    }
}

